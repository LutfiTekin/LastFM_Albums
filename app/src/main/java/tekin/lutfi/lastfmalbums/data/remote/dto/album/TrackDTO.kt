package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class TrackDTO(
    @field:Json(name = "artist")
    val artist: ArtistDTO?,
    @field:Json(name = "@attr")
    val attr: Attr?,
    @field:Json(name = "duration")
    val duration: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "streamable")
    val streamable: Streamable?,
    @field:Json(name = "url")
    val url: String?
)