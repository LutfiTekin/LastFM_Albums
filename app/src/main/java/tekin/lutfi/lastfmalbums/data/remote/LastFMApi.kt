package tekin.lutfi.lastfmalbums.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumResults
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResults
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumResults
import tekin.lutfi.lastfmalbums.utils.Constants.ALBUM
import tekin.lutfi.lastfmalbums.utils.Constants.ARTIST
import tekin.lutfi.lastfmalbums.utils.Constants.AUTO_CORRECT

interface LastFMApi {

    @GET("?method=artist.search")
    suspend fun searchArtists(
        @Query(ARTIST) query: String
    ): ArtistSearchResults

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(
        @Query(ARTIST) artist: String,
        @Query(AUTO_CORRECT) autoCorrect: String = "1"
    ): TopAlbumResults

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(
        @Query(ARTIST) artist: String,
        @Query(ALBUM) album: String
    ): AlbumResults

}