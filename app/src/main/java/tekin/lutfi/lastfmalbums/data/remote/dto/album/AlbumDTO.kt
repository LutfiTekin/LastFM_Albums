package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.Track
import tekin.lutfi.lastfmalbums.utils.Constants

data class AlbumDTO(
    @field:Json(name = "artist")
    val artist: String?,
    @field:Json(name = "image")
    val images: List<Image>?,
    @field:Json(name = "listeners")
    val listeners: String?,
    @field:Json(name = "mbid")
    val mbid: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "playcount")
    val playCount: String?,
    @field:Json(name = "tracks")
    val tracks: Tracks?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "wiki")
    val wiki: Wiki?
)

val AlbumDTO.album: Album
    get() {
        val largeImage = images?.find { it.size == Constants.IMAGE_SIZE_LARGE }?.text
        val tracks = tracks?.list?.map { Track(it.name, it.duration) } ?: emptyList()
        return Album(artist = artist, name = name, image = largeImage, tracks = tracks)
    }