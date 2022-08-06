package tekin.lutfi.lastfmalbums.data.remote.dto.artist


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.Artist
import tekin.lutfi.lastfmalbums.utils.Constants

data class ArtistDTO(
    @field:Json(name = "image")
    val images: List<Image>?,
    @field:Json(name = "listeners")
    val listeners: String?,
    @field:Json(name = "mbid")
    val mbid: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "streamable")
    val streamable: String?,
    @field:Json(name = "url")
    val url: String?
)

val ArtistDTO.artist: Artist
    get() {
        val largeImage = images?.find { it.size == Constants.IMAGE_SIZE_LARGE }?.text
        val listenerCount = listeners?.toLongOrNull() ?: 0L
        return Artist(name = name.orEmpty(), image = largeImage.orEmpty(), listeners = listenerCount)
    }
