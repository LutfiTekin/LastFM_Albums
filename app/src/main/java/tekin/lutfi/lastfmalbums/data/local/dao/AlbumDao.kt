package tekin.lutfi.lastfmalbums.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.utils.Constants

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ${Constants.ALBUM_TABLE}")
    fun getAlbums(): Flow<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(album: AlbumEntity)

    @Query("DELETE FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName")
    suspend fun deleteAlbum(albumName: String)

    @Query("SELECT COUNT() FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName")
    fun isFavorited(albumName: String): Flow<Int>

}