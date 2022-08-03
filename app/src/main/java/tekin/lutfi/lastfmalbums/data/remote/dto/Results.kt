package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class Results(
    @Json(name = "albummatches")
    val albumMatches: AlbumMatches?,
    @Json(name = "@attr")
    val attr: Attr?,
    @Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: String?,
    @Json(name = "opensearch:Query")
    val query: OpenSearchQuery?,
    @Json(name = "opensearch:startIndex")
    val startIndex: String?,
    @Json(name = "opensearch:totalResults")
    val totalResults: String?
)