package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class TopAlbums(
    @Json(name = "album")
    val albums: List<TopAlbumDTO>?,
    @Json(name = "@attr")
    val attribute: TopAlbumAttribute?
)