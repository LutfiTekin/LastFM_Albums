package tekin.lutfi.lastfmalbums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tekin.lutfi.lastfmalbums.databinding.ListItemAlbumBinding
import tekin.lutfi.lastfmalbums.domain.model.Album

class LocalAlbumsAdapter(private val localAlbumSelectionListener: LocalAlbumSelectionListener) :
    ListAdapter<Album, LocalAlbumViewHolder>(LocalAlbumsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalAlbumViewHolder {
        val binding = ListItemAlbumBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return LocalAlbumViewHolder(binding, localAlbumSelectionListener)
    }

    override fun onBindViewHolder(holder: LocalAlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}


class LocalAlbumViewHolder internal constructor(
    private val binding: ListItemAlbumBinding,
    private val localAlbumSelectionListener: LocalAlbumSelectionListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(album: Album) = binding.apply{
        albumName.text = album.name.orEmpty()
        artistName.text = album.artist.orEmpty()
        albumImage.load(album.image)
        root.setOnClickListener {
            localAlbumSelectionListener.onAlbumSelected(album)
        }

    }

}

interface LocalAlbumSelectionListener {
    fun onAlbumSelected(album: Album)
}

private class LocalAlbumsDiffUtil : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}