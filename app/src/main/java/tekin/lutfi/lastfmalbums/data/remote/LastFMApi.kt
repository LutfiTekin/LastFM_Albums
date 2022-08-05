package tekin.lutfi.lastfmalbums.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tekin.lutfi.lastfmalbums.data.remote.dto.SearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.TopAlbumsDTO
import tekin.lutfi.lastfmalbums.utils.Constants
import tekin.lutfi.lastfmalbums.utils.Constants.ALBUM
import tekin.lutfi.lastfmalbums.utils.Constants.ARTIST
import tekin.lutfi.lastfmalbums.utils.Constants.AUTO_CORRECT
import tekin.lutfi.lastfmalbums.utils.Constants.PAGE
import tekin.lutfi.lastfmalbums.utils.Resource

interface LastFMApi {

    @GET("?method=album.search")
    suspend fun searchAlbums(
        @Query(ALBUM) query: String
    ): SearchResultDTO

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(
        @Query(ARTIST) artist: String,
        @Query(AUTO_CORRECT) autoCorrect: String = "1"
    ): TopAlbumsDTO

}