package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Attribute(
    @field:Json(name = "for")
    val forX: String?
)