package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class OpensearchQuery(
    @field:Json(name = "role")
    val role: String?,
    @field:Json(name = "searchTerms")
    val searchTerms: String?,
    @field:Json(name = "startPage")
    val startPage: String?,
    @field:Json(name = "#text")
    val text: String?
)