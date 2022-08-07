package tekin.lutfi.lastfmalbums.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.Track
import tekin.lutfi.lastfmalbums.utils.Constants

@Entity(tableName = Constants.ALBUM_TABLE)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val artist: String?,
    val image: String?,
    val name: String?,
    val tracks: List<Track>
) {


}


val AlbumEntity.album: Album
    get() = Album(
        artist = artist,
        image = image,
        name = name,
        tracks = tracks
    )

val Album.albumEntity: AlbumEntity
    get() = AlbumEntity(
        artist = artist,
        image = image,
        name = name,
        tracks = tracks
    )