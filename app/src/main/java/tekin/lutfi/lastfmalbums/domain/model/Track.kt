package tekin.lutfi.lastfmalbums.domain.model

import tekin.lutfi.lastfmalbums.utils.toSongDuration


data class Track(
    val name: String?,
    val duration: Int?
) {

    val durationString: String
        get() = duration?.toSongDuration() ?: "n/a"

}
