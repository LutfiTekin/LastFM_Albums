package tekin.lutfi.lastfmalbums.domain.use_case.search

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.artist
import tekin.lutfi.lastfmalbums.domain.model.Artist
import tekin.lutfi.lastfmalbums.domain.repository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.utils.Resource
import tekin.lutfi.lastfmalbums.utils.sendRequest
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: LastFMAlbumRepository) {


    suspend fun searchArtist(query: String): Flow<Resource<List<Artist>?>> = sendRequest {
        repository.searchArtists(query)
    }.map { resource ->
        when(resource){
            is Resource.Success -> {
                Resource.Success(resource.data.results?.artistMatches?.artist?.map { it.artist })
            }
            is Resource.Loading -> Resource.Loading
            is Resource.Error -> Resource.Error(resource.e)
        }
    }



}