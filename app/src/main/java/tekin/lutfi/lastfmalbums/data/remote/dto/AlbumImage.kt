package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class AlbumImage(
    @Json(name = "size")
    val size: String?,
    @Json(name = "#text")
    val text: String?
)