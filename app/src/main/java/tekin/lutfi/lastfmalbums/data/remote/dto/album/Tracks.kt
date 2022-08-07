package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.*
import tekin.lutfi.lastfmalbums.domain.model.Track
import kotlin.collections.ArrayList


@Suppress("UNCHECKED_CAST")
data class Tracks(
    @field:Json(name = "track")
    val list: Any?
){
    fun getTrackList(): List<Track>{
        return if (list is ArrayList<*>){
            (list as List<Map<String, Any>>).map { it.toTrack() }
        }else {
            return listOf((list as Map<String, Any>).toTrack())
        }
    }

}

/**
 * Had to use a custom mapper here
 * as Last FM api returns an object when there is only one track
 */
fun Map<String, Any>.toTrack(): Track{
    val name = this["name"] as String?
    val duration = this["duration"] as Double?
    return Track(name, duration?.toInt())
}