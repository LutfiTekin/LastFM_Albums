package tekin.lutfi.lastfmalbums.utils

import android.os.Handler
import android.os.Looper
import android.view.View

class DebouncedClickListener(
    private var interval: Long = 1000,
    private val onDebouncedClick: (View) -> Unit
) : View.OnClickListener {
    override fun onClick(v: View) {
        v.isEnabled = false
        onDebouncedClick(v)
        Handler(Looper.getMainLooper()).postDelayed({
            v.isEnabled = true
        }, interval)
    }
}

fun View.setDebouncedClickListener(interval: Long = 1000L, onSafeClick: (View) -> Unit) {
    val debouncedClickListener = DebouncedClickListener(interval) {
        onSafeClick(it)
    }
    setOnClickListener(debouncedClickListener)
}