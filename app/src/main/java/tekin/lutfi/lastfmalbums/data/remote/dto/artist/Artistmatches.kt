package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json

data class Artistmatches(
    @field:Json(name = "artist")
    val artist: List<ArtistDTO>?
)