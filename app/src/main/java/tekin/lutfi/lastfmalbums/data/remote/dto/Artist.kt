package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class Artist(
    @field:Json(name = "mbid")
    val mbid: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)