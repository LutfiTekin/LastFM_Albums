package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.Album

data class AlbumDTO(
    @field:Json(name = "artist")
    val artist: String?,
    @field:Json(name = "image")
    val image: List<AlbumImage>?,
    @field:Json(name = "mbid")
    val mbid: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "streamable")
    val streamable: String?,
    @field:Json(name = "url")
    val url: String?
)


val AlbumDTO.album: Album
    get() = Album(
        artist = artist,
        image = image?.firstOrNull()?.text,
        name = name,
        url = url
    )