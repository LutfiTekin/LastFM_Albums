package tekin.lutfi.lastfmalbums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.di.AppModule


@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
@TypeConverters(AppModule.TrackListTypeConverter::class)
abstract class AlbumDB: RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

}