package tekin.lutfi.lastfmalbums.domain.repository

import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity

interface LastFMLocalAlbumRepository {

    fun getAlbums(): Flow<List<AlbumEntity>>

    suspend fun getAlbum(albumName: String, artist: String): AlbumEntity

    suspend fun addAlbum(album: AlbumEntity)

    suspend fun deleteAlbum(albumName: String, artist: String)

    fun isFavorite(albumName: String, artist: String): Flow<Boolean>


}