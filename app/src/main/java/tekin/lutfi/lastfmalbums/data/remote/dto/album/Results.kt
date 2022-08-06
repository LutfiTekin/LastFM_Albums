package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Results(
    @field:Json(name = "albummatches")
    val albumMatches: AlbumMatches?,
    @field:Json(name = "@attr")
    val attribute: Attribute?,
    @field:Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: String?,
    @field:Json(name = "opensearch:Query")
    val query: OpenSearchQuery?,
    @field:Json(name = "opensearch:startIndex")
    val startIndex: String?,
    @field:Json(name = "opensearch:totalResults")
    val totalResults: String?
)