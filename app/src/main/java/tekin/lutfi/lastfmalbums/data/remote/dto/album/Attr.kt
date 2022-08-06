package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Attr(
    @field:Json(name = "rank")
    val rank: Int?
)