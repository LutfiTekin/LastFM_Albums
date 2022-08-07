package tekin.lutfi.lastfmalbums.data.repository


import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.data.local.dao.AlbumDao
import tekin.lutfi.lastfmalbums.data.local.entity.AlbumEntity
import tekin.lutfi.lastfmalbums.domain.repository.LastFMLocalAlbumRepository
import javax.inject.Inject

class LastFMLocalAlbumRepositoryImpl @Inject constructor(private val dao: AlbumDao): LastFMLocalAlbumRepository {

    override fun getAlbums(): Flow<List<AlbumEntity>> = dao.getAlbums()

    override suspend fun addAlbum(album: AlbumEntity) = dao.addAlbum(album)

    override suspend fun deleteAlbum(album: AlbumEntity) = dao.deleteAlbum(album)

    override fun isFavorited(albumName: String): Flow<Int> = dao.isFavorited(albumName)

}