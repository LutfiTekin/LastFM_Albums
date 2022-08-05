package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class TopAlbums(
    @field:Json(name = "album")
    val albums: List<TopAlbumDTO>?,
    @field:Json(name = "@attr")
    val attribute: TopAlbumAttribute?
)