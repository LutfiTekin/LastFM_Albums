package tekin.lutfi.lastfmalbums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.data.local.type_converter.TrackListTypeConverter


@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
@TypeConverters(TrackListTypeConverter::class)
abstract class AlbumDB: RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

}