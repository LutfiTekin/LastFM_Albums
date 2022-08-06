package tekin.lutfi.lastfmalbums.data.repository

import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.data.remote.dto.album.AlbumSearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.artist.ArtistSearchResultsDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.top_album.TopAlbumsDTO
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import javax.inject.Inject

class LastFMAlbumRepositoryImpl @Inject constructor(private val api: LastFMApi): LastFMAlbumRepository {

    override suspend fun searchAlbums(query: String): AlbumSearchResultDTO =
        api.searchAlbums(query)

    override suspend fun searchArtists(query: String): ArtistSearchResultsDTO =
        api.searchArtists(query)

    override suspend fun getTopAlbums(artist: String): TopAlbumsDTO =
        api.getTopAlbums(artist)
}