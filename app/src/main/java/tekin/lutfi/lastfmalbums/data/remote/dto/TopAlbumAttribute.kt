package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class TopAlbumAttribute(
    @field:Json(name = "artist")
    val artist: String?,
    @field:Json(name = "page")
    val page: String?,
    @field:Json(name = "perPage")
    val perPage: String?,
    @field:Json(name = "total")
    val total: String?,
    @field:Json(name = "totalPages")
    val totalPages: String?
)