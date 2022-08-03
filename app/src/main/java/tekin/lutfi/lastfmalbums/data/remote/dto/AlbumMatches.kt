package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class AlbumMatches(
    @Json(name = "album")
    val album: List<Album>?
)