package tekin.lutfi.lastfmalbums.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumSearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResultsDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumsDTO
import tekin.lutfi.lastfmalbums.utils.Constants.ALBUM
import tekin.lutfi.lastfmalbums.utils.Constants.ARTIST
import tekin.lutfi.lastfmalbums.utils.Constants.AUTO_CORRECT

interface LastFMApi {

    @GET("?method=album.search")
    suspend fun searchAlbums(
        @Query(ALBUM) query: String
    ): AlbumSearchResultDTO

    @GET("?method=artist.search")
    suspend fun searchArtists(
        @Query(ARTIST) query: String
    ): ArtistSearchResultsDTO

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(
        @Query(ARTIST) artist: String,
        @Query(AUTO_CORRECT) autoCorrect: String = "1"
    ): TopAlbumsDTO

}