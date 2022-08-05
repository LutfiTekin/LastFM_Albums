package tekin.lutfi.lastfmalbums.ui.home

import tekin.lutfi.lastfmalbums.domain.model.Album

data class AlbumUIState(
    val isLoading: Boolean = false,
    val list: List<Album>? = null,
    val error: String? = null
)
