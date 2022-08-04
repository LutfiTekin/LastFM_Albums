package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.Album

data class AlbumDTO(
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "image")
    val image: List<AlbumImage>?,
    @Json(name = "mbid")
    val mbid: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "streamable")
    val streamable: String?,
    @Json(name = "url")
    val url: String?
)


val AlbumDTO.album: Album
    get() = Album(
        artist = artist,
        image = image?.firstOrNull()?.text,
        name = name,
        url = url
    )