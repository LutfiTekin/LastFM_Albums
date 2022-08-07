package tekin.lutfi.lastfmalbums.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.databinding.FragmentHomeBinding
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.ui.adapter.LocalAlbumSelectionListener
import tekin.lutfi.lastfmalbums.ui.adapter.LocalAlbumsAdapter
import tekin.lutfi.lastfmalbums.ui.fragment.top_albums.TopAlbumsFragmentDirections

@AndroidEntryPoint
class HomeFragment : Fragment(), LocalAlbumSelectionListener {

    //region Setup ViewBinding
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    private val homeViewModel: HomeViewModel by viewModels()

    private val localAlbumsAdapter: LocalAlbumsAdapter by lazy {
        LocalAlbumsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.setupUI()
        loadFavoriteAlbums()
        return root
    }

    private fun loadFavoriteAlbums() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.getAlbums().collectLatest { albumList ->
                    binding.emptyState.root.isVisible = albumList.isEmpty()
                    localAlbumsAdapter.submitList(albumList)
                }
            }
        }
    }

    private fun FragmentHomeBinding.setupUI(){
        albumList.apply {
            setHasFixedSize(true)
            adapter = localAlbumsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onAlbumSelected(album: Album) {
        val action = HomeFragmentDirections.actionHomeToDetail(album)
        findNavController().navigate(action)
    }
}