package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.R
import tekin.lutfi.lastfmalbums.databinding.AlbumDetailFragmentBinding
import tekin.lutfi.lastfmalbums.ui.adapter.TracksAdapter
import tekin.lutfi.lastfmalbums.utils.setFavorite

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {


    //region Viewbinding Setup
    private var _binding: AlbumDetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    private val args: AlbumDetailFragmentArgs by navArgs()

    private val tracksAdapter: TracksAdapter by lazy { TracksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        binding.setupUI()
        albumDetailViewModel.loadAlbumTracks(args.album)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumDetailViewModel.albumInfoState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading
                    if (state.error.isNullOrBlank()) {
                        tracksAdapter.submitList(state.data?.tracks)
                        binding.albumItem.favoriteButton.isVisible = true
                    } else {
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumDetailViewModel.favoriteButtonState.collectLatest { state ->
                    if (state.data != null) {
                        binding.albumItem.favoriteButton.setFavorite(state.data)
                    }
                }
            }
        }

    }


    private fun AlbumDetailFragmentBinding.setupUI() {
        albumItem.apply {
            albumName.text = args.album.name
            artistName.text = args.album.artist
            albumImage.load(args.album.image)
            favoriteButton.setOnClickListener {
                val isFavorite = albumDetailViewModel.favoriteButtonState.value.data ?: false
                val album =
                    albumDetailViewModel.albumInfoState.value.data ?: return@setOnClickListener
                val message = if (isFavorite) {
                    albumDetailViewModel.removeAlbumFromFavorites(album)
                    String.format(getString(R.string.toast_album_removed), album.name, album.artist)
                } else {
                    albumDetailViewModel.addAlbumToFavorites(album)
                    String.format(getString(R.string.toast_album_added), album.name, album.artist)
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        trackList.apply {
            setHasFixedSize(true)
            adapter = tracksAdapter
            addItemDecoration(
                DividerItemDecoration(
                    trackList.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}
