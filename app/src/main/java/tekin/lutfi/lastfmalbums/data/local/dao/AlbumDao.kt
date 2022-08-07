package tekin.lutfi.lastfmalbums.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.utils.Constants

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ${Constants.ALBUM_TABLE}")
    fun getAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName AND artist = :artist")
    suspend fun getAlbum(albumName: String, artist: String): AlbumEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(album: AlbumEntity)

    @Query("DELETE FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName AND artist = :artist")
    suspend fun deleteAlbum(albumName: String, artist: String)

    @Query("SELECT EXISTS(SELECT name FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName AND artist = :artist)")
    fun isFavoriteState(albumName: String, artist: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT name FROM ${Constants.ALBUM_TABLE} WHERE name = :albumName AND artist = :artist)")
    suspend fun isFavorite(albumName: String, artist: String): Boolean

}