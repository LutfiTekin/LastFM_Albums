package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class ArtistSearchResultsDTO(
    @field:Json(name = "results")
    val results: Results?
)