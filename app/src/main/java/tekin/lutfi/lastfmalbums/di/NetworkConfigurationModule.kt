package tekin.lutfi.lastfmalbums.di

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Request
import retrofit2.converter.moshi.MoshiConverterFactory
import tekin.lutfi.lastfmalbums.utils.Constants
import tekin.lutfi.lastfmalbums.utils.Constants.API_KEY
import tekin.lutfi.lastfmalbums.utils.Constants.BASE_URL
import tekin.lutfi.lastfmalbums.utils.Constants.CACHE_DIR
import tekin.lutfi.lastfmalbums.utils.Constants.CACHE_SIZE
import tekin.lutfi.lastfmalbums.utils.Constants.DEFAULT_TIMEOUT
import tekin.lutfi.lastfmalbums.utils.Constants.FORMAT
import tekin.lutfi.lastfmalbums.utils.Constants.MOSHI_CONVERTER_FACTORY
import tekin.lutfi.lastfmalbums.utils.Constants.PAGE_LIMIT
import tekin.lutfi.lastfmalbums.utils.Constants.RESPONSE_CACHE
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkConfigurationModule {

    @Singleton
    @Provides
    @Named(BASE_URL)
    fun providesBaseUrl() = "https://ws.audioscrobbler.com/2.0/"

    @Singleton
    @Provides
    @Named(CACHE_DIR)
    fun providesCacheDir() = "rf_cache"

    @Singleton
    @Provides
    @Named(PAGE_LIMIT)
    fun providesPageLimit() = "30"

    @Singleton
    @Provides
    @Named(DEFAULT_TIMEOUT)
    fun providesDefaultHttpTimeout() = 20000L

    @Singleton
    @Provides
    @Named(CACHE_SIZE)
    fun provideCacheSize() = 50L * 1024L * 1024L

    @Singleton
    @Provides
    @Named(MOSHI_CONVERTER_FACTORY)
    fun providesMoshiConverter() = MoshiConverterFactory.create()

    @Singleton
    @Provides
    @Named(FORMAT)
    fun providesRequestFormat() = "json"


    @Singleton
    @Provides
    @Named(RESPONSE_CACHE)
    fun providesResponseCache(
        @ApplicationContext context: Context,
        @Named(CACHE_DIR) cacheDir: String,
        @Named(CACHE_SIZE) cacheSize: Long
    ) = Cache(File(context.cacheDir, cacheDir), cacheSize)

    @Singleton
    @Provides
    @Named(API_KEY)
    fun providesApiKey() = "f938174428643e3a31477fc7653e4d09"
    //fun providesApiKey() = Firebase.remoteConfig.getString(API_KEY)


}