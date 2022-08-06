package tekin.lutfi.lastfmalbums.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

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

suspend inline fun<T: Any> sendRequest(crossinline request: suspend () -> T) = flow {
    emit(Resource.Loading)
    val result = request()
    emit(Resource.Success(result))
}.catch { e ->
    emit(Resource.Error(e))
}