package tekin.lutfi.lastfmalbums.di

import android.content.Context
import androidx.room.ProvidedTypeConverter
import androidx.room.Room
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tekin.lutfi.lastfmalbums.data.local.AlbumDB
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.domain.model.Track
import tekin.lutfi.lastfmalbums.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLastFMApi(retrofit: Retrofit): LastFMApi = retrofit.create(LastFMApi::class.java)

    @Singleton
    @Provides
    fun provideAlbumDB(
        @ApplicationContext context: Context,
        trackListTypeConverter: TrackListTypeConverter
    ): AlbumDB =
        Room.databaseBuilder(context, AlbumDB::class.java, Constants.ALBUM_TABLE)
            .addTypeConverter(trackListTypeConverter).build()

    @Singleton
    @Provides
    fun provideAlbumDao(db: AlbumDB): AlbumDao = db.getAlbumDao()

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @ProvidedTypeConverter
    class TrackListTypeConverter @Inject constructor(private val moshi: Moshi){

        @TypeConverter
        fun fromStringToList(string: String): List<Track>?{
            val type = Types.newParameterizedType(List::class.java, Track::class.java)
            return moshi.adapter<List<Track>>(type).fromJson(string)
        }

        @TypeConverter
        fun fromListToString(list: List<Track>): String{
            val type = Types.newParameterizedType(List::class.java, Track::class.java)
            return moshi.adapter<List<Track>>(type).toJson(list)
        }
    }

}