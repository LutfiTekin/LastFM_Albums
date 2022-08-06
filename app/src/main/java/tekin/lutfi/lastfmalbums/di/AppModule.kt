package tekin.lutfi.lastfmalbums.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tekin.lutfi.lastfmalbums.data.local.AlbumDB
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLastFMApi(retrofit: Retrofit): LastFMApi {
        return retrofit.create(LastFMApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumDB(@ApplicationContext context: Context): AlbumDB{
        return Room.databaseBuilder(context, AlbumDB::class.java, Constants.ALBUM_TABLE).build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(db: AlbumDB): AlbumDao{
        return db.getAlbumDao()
    }

}