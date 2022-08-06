package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json

data class Tracks(
    @field:Json(name = "track")
    val list: List<TrackDTO>?
)