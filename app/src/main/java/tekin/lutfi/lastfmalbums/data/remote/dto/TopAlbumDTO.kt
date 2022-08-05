package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum

data class TopAlbumDTO(
    @field:Json(name = "artist")
    val artist: Artist?,
    @field:Json(name = "image")
    val image: List<AlbumImage>?,
    @field:Json(name = "mbid")
    val mbid: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "playcount")
    val playCount: Int?,
    @field:Json(name = "url")
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