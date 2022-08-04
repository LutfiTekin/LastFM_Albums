package tekin.lutfi.lastfmalbums.domain.use_case.top_albums

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tekin.lutfi.lastfmalbums.data.remote.dto.album
import tekin.lutfi.lastfmalbums.data.remote.dto.topAlbum
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

class TopAlbumUseCase @Inject constructor(private val repository: LastFMAlbumRepository) {

    operator fun invoke(artist: String, page: String): Flow<Resource<List<TopAlbum>>> = flow{
        try {
            emit(Resource.Loading())
            val data = repository.getTopAlbums(artist, page).topAlbums?.albums?.map { it.topAlbum }
                ?: throw Exception()
            emit(Resource.Success(data))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }catch (e: Exception){
            emit(Resource.Error("An error occurred"))
        }
    }

}