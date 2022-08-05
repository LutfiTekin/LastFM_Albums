package tekin.lutfi.lastfmalbums.data.remote.dto


import com.squareup.moshi.Json

data class TopAlbumsDTO(
    @field:Json(name = "topalbums")
    val topAlbums: TopAlbums?
)