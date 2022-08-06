package tekin.lutfi.lastfmalbums.data.repository

import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumResults
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResults
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumResults
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import javax.inject.Inject

class LastFMAlbumRepositoryImpl @Inject constructor(private val api: LastFMApi): LastFMAlbumRepository {


    override suspend fun searchArtists(query: String): ArtistSearchResults =
        api.searchArtists(query)

    override suspend fun getTopAlbums(artist: String): TopAlbumResults =
        api.getTopAlbums(artist)

    override suspend fun getAlbumInfo(artist: String, album: String): AlbumResults =
        api.getAlbumInfo(artist, album)
}