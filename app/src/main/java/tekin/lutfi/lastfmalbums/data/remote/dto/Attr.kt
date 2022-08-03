package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class Attr(
    @Json(name = "for")
    val forX: String?
)