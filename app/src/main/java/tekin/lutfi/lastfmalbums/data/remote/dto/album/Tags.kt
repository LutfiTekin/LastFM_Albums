package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Tags(
    @field:Json(name = "tag")
    val tag: List<Tag>?
)