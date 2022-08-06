package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Streamable(
    @field:Json(name = "fulltrack")
    val fulltrack: String?,
    @field:Json(name = "#text")
    val text: String?
)