package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class Album(
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "image")
    val image: List<Image>?,
    @Json(name = "mbid")
    val mbid: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "streamable")
    val streamable: String?,
    @Json(name = "url")
    val url: String?
)