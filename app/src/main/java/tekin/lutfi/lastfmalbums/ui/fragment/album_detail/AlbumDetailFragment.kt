package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.databinding.AlbumDetailFragmentBinding
import tekin.lutfi.lastfmalbums.ui.adapter.TracksAdapter

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
        loadTracks()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        if (args.album.tracks.isEmpty()) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    albumDetailViewModel.albumInfoState.collect{ state ->
                        binding.progressBar.isVisible = state.isLoading
                        if (state.error.isNullOrBlank()) {
                            tracksAdapter.submitList(state.data?.tracks)
                        } else {
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }else tracksAdapter.submitList(args.album.tracks)

    }

    private fun loadTracks() {
        albumDetailViewModel.loadAlbumTracks(args.album.artist.orEmpty(), args.album.name.orEmpty())
    }


    private fun AlbumDetailFragmentBinding.setupUI() {
        albumItem.albumName.text = args.album.name
        albumItem.artistName.text = args.album.artist
        albumItem.albumImage.load(args.album.image)
        trackList.apply {
            setHasFixedSize(true)
            adapter = tracksAdapter
            addItemDecoration(DividerItemDecoration(trackList.context, DividerItemDecoration.VERTICAL ))
        }
    }
}
