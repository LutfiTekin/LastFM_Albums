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

    operator fun invoke(query: String, page: String): Flow<Resource<List<Album>>> = flow{
        try {
            emit(Resource.Loading())
            val data = repository.searchAlbums(query, page).results?.albumMatches?.album?.map { it.album }
                ?: throw Exception()
            emit(Resource.Success(data))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }catch (e: Exception){
            //TODO handle strings
            emit(Resource.Error("An error occurred"))
        }
    }

}