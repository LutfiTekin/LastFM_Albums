package tekin.lutfi.lastfmalbums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tekin.lutfi.lastfmalbums.databinding.ListItemTopAlbumBinding
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum

class TopAlbumsAdapter(private val topAlbumSelectionListener: TopAlbumSelectionListener) :
    ListAdapter<TopAlbum, TopAlbumViewHolder>(TopAlbumsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumViewHolder {
        val binding = ListItemTopAlbumBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return TopAlbumViewHolder(binding, topAlbumSelectionListener)
    }

    override fun onBindViewHolder(holder: TopAlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class TopAlbumViewHolder internal constructor(
    private val binding: ListItemTopAlbumBinding,
    private val topAlbumSelectionListener: TopAlbumSelectionListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(album: TopAlbum) = binding.apply{
        albumName.text = album.name.orEmpty()
        albumImage.load(album.image)
        root.setOnClickListener {
            topAlbumSelectionListener.onTopAlbumSelected(album)
        }
    }

}

interface TopAlbumSelectionListener {
    fun onTopAlbumSelected(album: TopAlbum)
}

private class TopAlbumsDiffUtil : DiffUtil.ItemCallback<TopAlbum>() {
    override fun areItemsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
        return oldItem == newItem
    }
}