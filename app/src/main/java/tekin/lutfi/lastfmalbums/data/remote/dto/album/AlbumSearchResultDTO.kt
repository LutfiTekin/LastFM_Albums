package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class AlbumSearchResultDTO(
    @field:Json(name = "results")
    val results: Results?
)