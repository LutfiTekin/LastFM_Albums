package tekin.lutfi.lastfmalbums.data.local.type_converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import tekin.lutfi.lastfmalbums.domain.model.Track
import javax.inject.Inject

@ProvidedTypeConverter
class TrackListTypeConverter @Inject constructor(private val moshi: Moshi){

    @TypeConverter
    fun fromStringToList(string: String): List<Track>?{
        val type = Types.newParameterizedType(List::class.java, Track::class.java)
        return moshi.adapter<List<Track>>(type).fromJson(string)
    }

    @TypeConverter
    fun fromListToString(list: List<Track>): String{
        val type = Types.newParameterizedType(List::class.java, Track::class.java)
        return moshi.adapter<List<Track>>(type).toJson(list)
    }
}