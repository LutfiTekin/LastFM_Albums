package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class Results(
    @field:Json(name = "artistmatches")
    val artistMatches: Artistmatches?,
    @field:Json(name = "@attr")
    val attribute: Attr?,
    @field:Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: String?,
    @field:Json(name = "opensearch:Query")
    val query: OpensearchQuery?,
    @field:Json(name = "opensearch:startIndex")
    val startIndex: String?,
    @field:Json(name = "opensearch:totalResults")
    val totalResults: String?
)