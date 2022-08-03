package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class OpenSearchQuery(
    @Json(name = "role")
    val role: String?,
    @Json(name = "searchTerms")
    val searchTerms: String?,
    @Json(name = "startPage")
    val startPage: String?,
    @Json(name = "#text")
    val text: String?
)