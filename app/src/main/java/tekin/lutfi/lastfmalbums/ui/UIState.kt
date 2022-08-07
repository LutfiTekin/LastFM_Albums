package tekin.lutfi.lastfmalbums.ui


data class UIState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null
)
