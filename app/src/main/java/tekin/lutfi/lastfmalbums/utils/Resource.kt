package tekin.lutfi.lastfmalbums.utils

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val e: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}