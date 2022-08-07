package tekin.lutfi.lastfmalbums.data.repository


import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import javax.inject.Inject

class LastFMLocalAlbumRepositoryImpl @Inject constructor(private val dao: AlbumDao): LastFMLocalAlbumRepository {

    override fun getAlbums(): Flow<List<AlbumEntity>> = dao.getAlbums()

    override suspend fun getAlbum(albumName: String, artist: String): AlbumEntity = dao.getAlbum(albumName, artist)

    override suspend fun addAlbum(album: AlbumEntity) = dao.addAlbum(album)

    override suspend fun deleteAlbum(albumName: String, artist: String) = dao.deleteAlbum(albumName, artist)

    override fun isFavoriteState(albumName: String, artist: String): Flow<Boolean> = dao.isFavoriteState(albumName, artist)

    override suspend fun isFavorite(albumName: String, artist: String): Boolean = dao.isFavorite(albumName, artist)

}