package tekin.lutfi.lastfmalbums.domain.model


data class TopAlbum(
    val artist: String?,
    val image: String?,
    val name: String?,
    val playCount: Int?,
    val url: String?,
    var isFavorite: Boolean = false
)

val TopAlbum.album
    get() = Album(artist, image, name, emptyList())