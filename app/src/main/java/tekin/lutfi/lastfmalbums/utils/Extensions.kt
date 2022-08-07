package tekin.lutfi.lastfmalbums.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import tekin.lutfi.lastfmalbums.R

val Context.isInternetAvailable: Boolean
    get() {
        val context = this
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

suspend inline fun <T : Any> sendRequest(crossinline request: suspend () -> T) = flow {
    emit(Resource.Loading)
    val result = request()
    emit(Resource.Success(result))
}.catch { e ->
    emit(Resource.Error(e))
}

fun View.hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun Int.toSongDuration(): String {
    val minutes: Int = this / 60
    var seconds = this.rem(60).toString()
    if (seconds.length == 1)
        seconds = "0$seconds"
    return "$minutes:$seconds"
}

fun AppCompatImageView.setFavorite(state: Boolean) {
    val drawable = ContextCompat.getDrawable(
        context,
        if (state) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
    )
    setImageDrawable(drawable)
}
