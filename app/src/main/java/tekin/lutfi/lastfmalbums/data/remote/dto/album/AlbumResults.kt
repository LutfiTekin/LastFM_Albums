package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class AlbumResults(
    @field:Json(name = "album")
    val album: AlbumDTO?
)