package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class Artist(
    @Json(name = "mbid")
    val mbid: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)