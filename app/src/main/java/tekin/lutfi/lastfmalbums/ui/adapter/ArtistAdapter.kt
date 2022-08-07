package tekin.lutfi.lastfmalbums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tekin.lutfi.lastfmalbums.R
import tekin.lutfi.lastfmalbums.databinding.ListItemArtistBinding
import tekin.lutfi.lastfmalbums.domain.model.Artist

class ArtistAdapter(private val artistSelectionListener: ArtistSelectionListener) :
    ListAdapter<Artist, ArtistViewHolder>(ArtistDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding =
            ListItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding, artistSelectionListener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}


class ArtistViewHolder internal constructor(
    private val binding: ListItemArtistBinding,
    private val artistSelectionListener: ArtistSelectionListener
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(artist: Artist) = binding.apply {
        artistName.text = artist.name
        artistImage.load(artist.image)
        listenerCount.text = String.format(
            root.context.getString(R.string.listener_count),
            artist.formattedListeners
        )
        root.setOnClickListener {
            artistSelectionListener.onArtistSelected(artist)
        }
    }

}

interface ArtistSelectionListener {
    fun onArtistSelected(artist: Artist)
}

private class ArtistDiffUtil : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}