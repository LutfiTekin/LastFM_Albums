package tekin.lutfi.lastfmalbums.data.repository

import tekin.lutfi.lastfmalbums.data.remote.LastFMApi
import tekin.lutfi.lastfmalbums.data.remote.dto.SearchResultDTO
import tekin.lutfi.lastfmalbums.data.remote.dto.TopAlbumsDTO
import tekin.lutfi.lastfmalbums.domain.respository.LastFMAlbumRepository
import javax.inject.Inject

class LastFMAlbumRepositoryImpl @Inject constructor(private val api: LastFMApi): LastFMAlbumRepository {

    override suspend fun searchAlbums(query: String): SearchResultDTO =
        api.searchAlbums(query)

    override suspend fun getTopAlbums(artist: String): TopAlbumsDTO =
        api.getTopAlbums(artist)
}