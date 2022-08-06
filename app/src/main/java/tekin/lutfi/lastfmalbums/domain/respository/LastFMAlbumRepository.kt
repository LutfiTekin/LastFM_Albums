package tekin.lutfi.lastfmalbums.domain.respository

import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumResults
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResults
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumResults

interface LastFMAlbumRepository {

    suspend fun searchArtists(query: String): ArtistSearchResults

    suspend fun getTopAlbums(artist: String): TopAlbumResults

    suspend fun getAlbumInfo(artist: String, album: String): AlbumResults

}