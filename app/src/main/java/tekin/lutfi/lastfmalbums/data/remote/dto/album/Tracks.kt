package tekin.lutfi.lastfmalbums.data.remote.dto.album


import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types




@Suppress("UNCHECKED_CAST")
data class Tracks(
    @field:Json(name = "track")
    val list: List<TrackDTO>?
){
    fun getTrackList(): List<TrackDTO?>{
        return if (list is ArrayList<*>){
            list as List<TrackDTO>
        }else {
            /*val moshi = Moshi.Builder().build()
            val type = Types.newParameterizedType(LinkedHashMap::class.java, String::class.java, String::class.java)
            val jsonAdapter: JsonAdapter<LinkedHashMap<String, String>> = moshi.adapter(type)
            val json = jsonAdapter.toJson(list as LinkedHashMap<String, String>)
            val trackDTO = moshi.adapter(TrackDTO::class.java).fromJson(json)*/
                /*if (list is LinkedHashMap<*, *>){
                    return listOf(TrackDTO(list))
                }*/
            return listOf()
        }
    }
}