package tekin.lutfi.lastfmalbums.data.local.dao

import androidx.room.*
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.utils.Constants

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ${Constants.ALBUM_TABLE} LIMIT :size ")
    suspend fun getAlbums(size: Int): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(album: AlbumEntity)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)

}