package tekin.lutfi.lastfmalbums.domain.use_case.top_albums

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.topAlbum
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.repository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.utils.Resource
import tekin.lutfi.lastfmalbums.utils.sendRequest
import javax.inject.Inject

class TopAlbumsUseCase @Inject constructor(private val repository: LastFMAlbumRepository) {

    suspend fun getTopAlbums(artist: String): Flow<Resource<List<TopAlbum>?>> = sendRequest {
        repository.getTopAlbums(artist)
    }.map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(resource.data.topAlbums?.albums?.map { it.topAlbum })
            is Resource.Loading -> Resource.Loading
            is Resource.Error -> Resource.Error(resource.e)
        }
    }
}