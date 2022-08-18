package tekin.lutfi.lastfmalbums.domain.use_case.albums

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import tekin.lutfi.lastfmalbums.data.local.entity.album
import tekin.lutfi.lastfmalbums.data.local.entity.albumEntity
import tekin.lutfi.lastfmalbums.data.remote.dto.album.album
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.topAlbum
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.repository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import tekin.lutfi.lastfmalbums.utils.Resource
import tekin.lutfi.lastfmalbums.utils.sendRequest
import javax.inject.Inject

class AlbumUseCase @Inject constructor(private val repository: LastFMAlbumRepository, private val localAlbumRepository: LastFMLocalAlbumRepository) {


    suspend fun getAlbumInfo(artist: String, album: String): Flow<Resource<Album?>> = sendRequest {
        repository.getAlbumInfo(artist, album)
    }.map { resource ->
        when(resource){
            is Resource.Success -> Resource.Success(resource.data.album?.album)
            is Resource.Loading -> Resource.Loading
            is Resource.Error -> Resource.Error(resource.e)
        }
    }

    suspend fun localAlbumExists(artist: String, album: String): Boolean = localAlbumRepository.isFavorite(artist, album)

    suspend fun getAlbum(artist: String, album: String): Flow<Resource<Album?>>{
        return if (localAlbumExists(artist, album)) {
            flowOf(Resource.Success(getSingleAlbum(artist, album)))
        } else getAlbumInfo(artist, album)
    }

    fun loadAlbums(): Flow<List<Album>> = localAlbumRepository.getAlbums().map { entity ->
        entity.map { it.album }
    }

    private suspend fun getSingleAlbum(artist: String, albumName: String): Album =
        localAlbumRepository.getAlbum(albumName, artist).album

    suspend fun addAlbum(album: Album) = localAlbumRepository.addAlbum(album.albumEntity)

    suspend fun removeAlbum(album: Album) = localAlbumRepository.deleteAlbum(album.name.orEmpty(), album.artist.orEmpty())

    fun isFavouriteState(album: Album): Flow<Boolean> = localAlbumRepository.isFavoriteState(album.name.orEmpty(), album.artist.orEmpty())


}