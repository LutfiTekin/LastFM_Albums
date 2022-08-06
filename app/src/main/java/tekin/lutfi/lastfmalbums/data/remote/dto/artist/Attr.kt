package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class Attr(
    @field:Json(name = "for")
    val forX: String?
)