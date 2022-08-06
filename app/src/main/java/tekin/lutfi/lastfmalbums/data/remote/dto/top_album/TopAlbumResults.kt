package tekin.lutfi.lastfmalbums.data.remote.dto.top_album


import com.squareup.moshi.Json

data class TopAlbumResults(
    @field:Json(name = "topalbums")
    val topAlbums: TopAlbums?
)