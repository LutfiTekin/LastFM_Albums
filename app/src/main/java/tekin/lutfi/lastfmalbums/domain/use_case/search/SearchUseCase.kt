package tekin.lutfi.lastfmalbums.domain.use_case.search

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tekin.lutfi.lastfmalbums.data.remote.dto.AlbumDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.album
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: LastFMAlbumRepository) {

    operator fun invoke(query: String): Flow<Resource<List<Album>>> = flow{
        try {
            emit(Resource.Loading())
            val results = repository.searchAlbums(query).results
            val data = results?.albumMatches?.album?.map { it.album }
                data ?: throw Exception("Something happened")
            emit(Resource.Success(data))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }catch (e: Exception){
            //TODO handle strings
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }

}