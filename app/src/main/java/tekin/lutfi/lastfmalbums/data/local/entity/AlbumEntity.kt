package tekin.lutfi.lastfmalbums.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.Track
import tekin.lutfi.lastfmalbums.utils.Constants

@Entity(tableName = Constants.ALBUM_TABLE)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val artist: String?,
    val image: String?,
    val name: String?,
    val tracks: List<Track>
){

    class Converter{

        private val moshi = Moshi.Builder().build()

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

}


val AlbumEntity.album: Album
    get() = Album(
        artist = artist,
        image = image,
        name = name,
        tracks = tracks
    )