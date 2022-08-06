package tekin.lutfi.lastfmalbums.domain.model

import java.io.Serializable

data class Artist(
    val name: String,
    val image: String,
    val listeners: Long
    ): Serializable
