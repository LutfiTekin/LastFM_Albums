package tekin.lutfi.lastfmalbums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tekin.lutfi.lastfmalbums.databinding.ListItemTopAlbumBinding
import tekin.lutfi.lastfmalbums.databinding.ListItemTrackBinding
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.model.Track
import tekin.lutfi.lastfmalbums.utils.toSongDuration

class TracksAdapter() :
    ListAdapter<Track, TracksViewHolder>(TracksDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val binding =
            ListItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TracksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class TracksViewHolder internal constructor(
    private val binding: ListItemTrackBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(track: Track) = binding.apply {
        trackName.text = track.name
        trackDuration.text = track.durationString
    }

}


private class TracksDiffUtil : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}