package tekin.lutfi.lastfmalbums.domain.model


data class Album(
    val artist: String?,
    val image: String?,
    val name: String?,
    val tracks: List<Track>
)