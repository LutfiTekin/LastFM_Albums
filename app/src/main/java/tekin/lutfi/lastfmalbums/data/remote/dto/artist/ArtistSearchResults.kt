package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class ArtistSearchResults(
    @field:Json(name = "results")
    val results: Results?
)