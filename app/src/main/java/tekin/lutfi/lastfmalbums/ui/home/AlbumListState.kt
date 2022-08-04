package tekin.lutfi.lastfmalbums.ui.home

import tekin.lutfi.lastfmalbums.domain.model.Album

data class AlbumListState(
    val isLoading: Boolean = false,
    val list: List<Album>,
    val error: String = ""
)
