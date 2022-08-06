package tekin.lutfi.lastfmalbums.domain.model

import java.io.Serializable


data class Album(
    val artist: String?,
    val image: String?,
    val name: String?,
    val tracks: List<Track>
): Serializable