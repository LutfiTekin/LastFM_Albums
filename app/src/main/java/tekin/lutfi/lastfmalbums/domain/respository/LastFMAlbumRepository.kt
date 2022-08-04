package tekin.lutfi.lastfmalbums.domain.respository

import androidx.annotation.IntRange
import tekin.lutfi.lastfmalbums.data.remote.dto.SearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.TopAlbumsDTO

interface LastFMAlbumRepository {

    suspend fun searchAlbums(query: String, page: String): SearchResultDTO

    suspend fun getTopAlbums(artist: String, page: String): TopAlbumsDTO

}