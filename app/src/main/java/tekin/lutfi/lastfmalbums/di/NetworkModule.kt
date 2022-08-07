package tekin.lutfi.lastfmalbums.di

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tekin.lutfi.lastfmalbums.BuildConfig
import tekin.lutfi.lastfmalbums.utils.Constants
import tekin.lutfi.lastfmalbums.utils.Constants.API_KEY
import tekin.lutfi.lastfmalbums.utils.Constants.API_KEY_INTERCEPTOR
import tekin.lutfi.lastfmalbums.utils.Constants.CACHE_INTERCEPTOR
import tekin.lutfi.lastfmalbums.utils.Constants.DEFAULT_TIMEOUT
import tekin.lutfi.lastfmalbums.utils.Constants.FORMAT
import tekin.lutfi.lastfmalbums.utils.Constants.LOGGING_INTERCEPTOR
import tekin.lutfi.lastfmalbums.utils.Constants.PAGE_LIMIT
import tekin.lutfi.lastfmalbums.utils.Constants.RESPONSE_CACHE
import tekin.lutfi.lastfmalbums.utils.isInternetAvailable
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        @Named(Constants.BASE_URL) baseUrl: String,
        @Named(Constants.MOSHI_CONVERTER_FACTORY) moshi: MoshiConverterFactory,
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(moshi)
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(
        @Named(API_KEY_INTERCEPTOR) apiKeyInterceptor: Interceptor,
        @Named(CACHE_INTERCEPTOR) cacheInterceptor: Interceptor,
        @Named(LOGGING_INTERCEPTOR) loggingInterceptor: HttpLoggingInterceptor,
        @Named(DEFAULT_TIMEOUT) defaultTimeOut: Long,
        @Named(RESPONSE_CACHE) responseCache: Cache
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(defaultTimeOut, TimeUnit.MILLISECONDS)
        .writeTimeout(defaultTimeOut, TimeUnit.MILLISECONDS)
        .readTimeout(defaultTimeOut, TimeUnit.MILLISECONDS)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(cacheInterceptor)
        .addInterceptor(loggingInterceptor)
        .cache(responseCache).build()

    /**
     * Supply every request with api key query parameters
     */
    @Provides
    @Singleton
    @Named(API_KEY_INTERCEPTOR)
    fun providesApiKeyInterceptor(
        @Named(FORMAT) format: String,
        @Named(PAGE_LIMIT) limit: String
    ) = Interceptor { chain ->
        var request: Request = chain.request()
        val key = Firebase.remoteConfig.getString(API_KEY)
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, key)
            .addQueryParameter(FORMAT, format)
            .addQueryParameter(PAGE_LIMIT, limit).build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR)
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG)
            level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    @Named(CACHE_INTERCEPTOR)
    fun provideCacheInterceptor(@ApplicationContext context: Context) = Interceptor { chain ->
        var request = chain.request()
        val cacheControl =
            if (context.isInternetAvailable) CacheControl.FORCE_NETWORK else CacheControl.FORCE_CACHE
        request = request
            .newBuilder()
            .cacheControl(cacheControl)
            .build()
        chain.proceed(request)
    }

}