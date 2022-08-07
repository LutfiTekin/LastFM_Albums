package tekin.lutfi.lastfmalbums.domain.repository

import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity

interface LastFMLocalAlbumRepository {

    fun getAlbums(): Flow<List<AlbumEntity>>

    suspend fun addAlbum(album: AlbumEntity)

    suspend fun deleteAlbum(albumName: String)

    fun isFavorited(albumName: String): Flow<Int>


}