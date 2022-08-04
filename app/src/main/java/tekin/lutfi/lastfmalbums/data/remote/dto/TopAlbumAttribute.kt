package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class TopAlbumAttribute(
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "page")
    val page: String?,
    @Json(name = "perPage")
    val perPage: String?,
    @Json(name = "total")
    val total: String?,
    @Json(name = "totalPages")
    val totalPages: String?
)