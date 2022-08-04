package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum

data class TopAlbumDTO(
    @Json(name = "artist")
    val artist: Artist?,
    @Json(name = "image")
    val image: List<AlbumImage>?,
    @Json(name = "mbid")
    val mbid: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "playcount")
    val playCount: Int?,
    @Json(name = "url")
    val url: String?
)

val TopAlbumDTO.topAlbum: TopAlbum
    get() = TopAlbum(
        artist = artist?.name,
        image = image?.firstOrNull()?.text,
        name = name,
        playCount = playCount,
        url = url
    )