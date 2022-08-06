package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Wiki(
    @field:Json(name = "content")
    val content: String?,
    @field:Json(name = "published")
    val published: String?,
    @field:Json(name = "summary")
    val summary: String?
)