package tekin.lutfi.lastfmalbums.domain.use_case.local_albums

import kotlinx.coroutines.flow.*
import tekin.lutfi.lastfmalbums.data.local.entity.album
import tekin.lutfi.lastfmalbums.data.local.entity.albumEntity
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import javax.inject.Inject

class LocalAlbumsUseCase @Inject constructor(private val localAlbumRepository: LastFMLocalAlbumRepository) {

    suspend fun loadAlbums(): Flow<List<Album>> = localAlbumRepository.getAlbums().map { entity ->
        entity.map { it.album }
    }

    suspend fun addAlbum(album: Album) = localAlbumRepository.addAlbum(album.albumEntity)

    suspend fun removeAlbum(album: Album) = localAlbumRepository.deleteAlbum(album.name.orEmpty())

    fun isFavorited(albumName: String): Flow<Boolean> =
        localAlbumRepository.isFavorited(albumName).map {
            it == 1
        }

}