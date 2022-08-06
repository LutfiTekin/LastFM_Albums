package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class AlbumMatches(
    @field:Json(name = "album")
    val album: List<AlbumDTO>?
)