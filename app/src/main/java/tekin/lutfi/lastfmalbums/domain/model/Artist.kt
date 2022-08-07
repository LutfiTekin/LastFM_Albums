package tekin.lutfi.lastfmalbums.domain.model

import java.io.Serializable
import java.text.DecimalFormat

data class Artist(
    val name: String,
    val image: String,
    val listeners: Long
    ): Serializable{

        val formattedListeners: String
            get() {
                val decimalFormat = DecimalFormat("#,###")
                return decimalFormat.format(listeners)
            }

    }
