package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Tag(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)