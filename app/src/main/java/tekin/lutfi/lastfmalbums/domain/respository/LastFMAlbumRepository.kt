package tekin.lutfi.lastfmalbums.domain.respository

import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumSearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResultsDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumsDTO

interface LastFMAlbumRepository {

    suspend fun searchAlbums(query: String): AlbumSearchResultDTO

    suspend fun searchArtists(query: String): ArtistSearchResultsDTO

    suspend fun getTopAlbums(artist: String): TopAlbumsDTO

}