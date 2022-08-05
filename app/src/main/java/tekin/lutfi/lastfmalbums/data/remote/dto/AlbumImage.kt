package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class AlbumImage(
    @field:Json(name = "size")
    val size: String?,
    @field:Json(name = "#text")
    val text: String?
)