package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class SearchResultDTO(
    @Json(name = "results")
    val results: Results?
)