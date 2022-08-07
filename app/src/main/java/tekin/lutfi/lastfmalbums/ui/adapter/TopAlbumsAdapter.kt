package tekin.lutfi.lastfmalbums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tekin.lutfi.lastfmalbums.R
import tekin.lutfi.lastfmalbums.databinding.ListItemTopAlbumBinding
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.utils.Constants
import tekin.lutfi.lastfmalbums.utils.setDebouncedClickListener
import tekin.lutfi.lastfmalbums.utils.setFavorite

class TopAlbumsAdapter(private val topAlbumSelectionListener: TopAlbumSelectionListener) :
    ListAdapter<TopAlbum, TopAlbumViewHolder>(TopAlbumsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumViewHolder {
        val binding = ListItemTopAlbumBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return TopAlbumViewHolder(binding, topAlbumSelectionListener)
    }

    override fun onBindViewHolder(holder: TopAlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: TopAlbumViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(Constants.PAYLOAD_FAVORITE_BUTTON)){
            holder.setFavorite(getItem(position).isFavorite)
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

}


class TopAlbumViewHolder internal constructor(
    private val binding: ListItemTopAlbumBinding,
    private val topAlbumSelectionListener: TopAlbumSelectionListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(album: TopAlbum) = binding.apply{
        albumName.text = album.name.orEmpty()
        albumImage.load(album.image)
        favoriteButton.isVisible = true
        root.setOnClickListener {
            topAlbumSelectionListener.onTopAlbumSelected(album)
        }
        favoriteButton.setDebouncedClickListener {
            topAlbumSelectionListener.onTopAlbumAddedToFavorites(album.apply {
                isFavorite = !isFavorite
            })
        }
        setFavorite(album.isFavorite)
    }

    fun setFavorite(state: Boolean) = binding.favoriteButton.setFavorite(state)

}

interface TopAlbumSelectionListener {
    fun onTopAlbumSelected(topAlbum: TopAlbum)
    fun onTopAlbumAddedToFavorites(topAlbum: TopAlbum)
}

private class TopAlbumsDiffUtil : DiffUtil.ItemCallback<TopAlbum>() {
    override fun areItemsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
        return oldItem == newItem
    }
}