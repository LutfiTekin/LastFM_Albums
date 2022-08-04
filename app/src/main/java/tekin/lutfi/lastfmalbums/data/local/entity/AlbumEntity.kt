package tekin.lutfi.lastfmalbums.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.utils.Constants

@Entity(tableName = Constants.ALBUM_TABLE)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val artist: String?,
    val image: String?,
    val name: String?,
    val url: String?
)


val AlbumEntity.album: Album
    get() = Album(
        artist = artist,
        image = image,
        name = name,
        url = url
    )