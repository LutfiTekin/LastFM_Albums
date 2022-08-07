package tekin.lutfi.lastfmalbums.ui.fragment.top_albums

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.R
import tekin.lutfi.lastfmalbums.databinding.TopAlbumsFragmentBinding
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.model.album
import tekin.lutfi.lastfmalbums.ui.adapter.TopAlbumSelectionListener
import tekin.lutfi.lastfmalbums.ui.adapter.TopAlbumsAdapter

@AndroidEntryPoint
class TopAlbumsFragment : Fragment(), TopAlbumSelectionListener {

    //region Viewbinding Setup
    private var _binding: TopAlbumsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    private val topAlbumsViewModel: TopAlbumsViewModel by viewModels()

    private val args: TopAlbumsFragmentArgs by navArgs()

    private val topAlbumsAdapter: TopAlbumsAdapter by lazy {
        TopAlbumsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopAlbumsFragmentBinding.inflate(inflater, container, false)
        topAlbumsViewModel.loadTopAlbums(args.artist.name)
        binding.setupUI()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topAlbumsViewModel.topAlbumState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading
                    if (state.error.isNullOrBlank()) {
                        topAlbumsAdapter.submitList(state.data)
                    } else {
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun TopAlbumsFragmentBinding.setupUI() {
        artistItem.artistName.text = args.artist.name
        artistItem.listenerCount.text =
            String.format(root.context.getString(R.string.listener_count), args.artist.listeners)
        artistItem.artistImage.load(args.artist.image)
        topAlbumList.apply {
            setHasFixedSize(true)
            adapter = topAlbumsAdapter
            addItemDecoration(DividerItemDecoration(topAlbumList.context, DividerItemDecoration.VERTICAL ))
        }
    }

    override fun onTopAlbumSelected(topAlbum: TopAlbum) {
        val action = TopAlbumsFragmentDirections.actionTopAlbumToDetail(topAlbum.album)
        findNavController().navigate(action)
    }
}
