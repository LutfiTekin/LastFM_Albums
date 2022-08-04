package tekin.lutfi.lastfmalbums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity


@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumDB: RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

}