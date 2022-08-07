package tekin.lutfi.lastfmalbums.domain.use_case.local_albums

import kotlinx.coroutines.flow.*
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.data.local.entity.album
import tekin.lutfi.lastfmalbums.data.local.entity.albumEntity
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import javax.inject.Inject

class LocalAlbumsUseCase @Inject constructor(private val localAlbumRepository: LastFMLocalAlbumRepository) {

    fun loadAlbums(): Flow<List<Album>> = localAlbumRepository.getAlbums().map { entity ->
        entity.map { it.album }
    }

    suspend fun getSingleAlbum(albumName: String, artist: String): Album =
        localAlbumRepository.getAlbum(albumName, artist).album

    suspend fun addAlbum(album: Album) = localAlbumRepository.addAlbum(album.albumEntity)

    suspend fun removeAlbum(album: Album) = localAlbumRepository.deleteAlbum(album.name.orEmpty(), album.artist.orEmpty())

    fun isFavourite(album: Album): Flow<Boolean> = localAlbumRepository.isFavorite(album.name.orEmpty(), album.artist.orEmpty())

}