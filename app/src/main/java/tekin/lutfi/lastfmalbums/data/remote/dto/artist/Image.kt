package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "size")
    val size: String?,
    @field:Json(name = "#text")
    val text: String?
)