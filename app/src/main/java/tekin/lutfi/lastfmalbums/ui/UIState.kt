package tekin.lutfi.lastfmalbums.ui

import tekin.lutfi.lastfmalbums.domain.model.Album

data class UIState<T>(
    val isLoading: Boolean = false,
    val list: List<T>? = null,
    val error: String? = null
)
