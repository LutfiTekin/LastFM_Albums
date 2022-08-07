package tekin.lutfi.lastfmalbums.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.R
import tekin.lutfi.lastfmalbums.databinding.FragmentSearchBinding
import tekin.lutfi.lastfmalbums.domain.model.Artist
import tekin.lutfi.lastfmalbums.ui.adapter.ArtistAdapter
import tekin.lutfi.lastfmalbums.ui.adapter.ArtistSelectionListener
import tekin.lutfi.lastfmalbums.utils.Constants
import tekin.lutfi.lastfmalbums.utils.hideKeyboard

@AndroidEntryPoint
class SearchFragment : Fragment(), ArtistSelectionListener {

    //region Viewbinding Setup
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    //region Variables
    private val searchViewModel: SearchViewModel by viewModels()

    private val artistAdapter: ArtistAdapter by lazy {
        ArtistAdapter(this)
    }
    //endregion

    //region LifeCycle methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.setupUI()
        initObservers()
        return binding.root
    }
    //endregion

    //region Observers
    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                searchViewModel.searchState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading
                    if (state.error.isNullOrBlank()) {
                        artistAdapter.submitList(state.data)
                    }else {
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    //endregion

    //region UI Setup
    private fun FragmentSearchBinding.setupUI(){

        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isBlank()){
                Toast.makeText(context, getString(R.string.error_empty_search_query), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            searchViewModel.runSearch(query)
            searchInput.hideKeyboard()
        }
        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchButton.performClick()
                return@setOnEditorActionListener true
            }
            false
        }
        artistList.apply {
            adapter = artistAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(artistList.context, DividerItemDecoration.VERTICAL ))
        }
    }
    //endregion

    //region Listeners
    override fun onArtistSelected(artist: Artist) {
        val action = SearchFragmentDirections.actionLaunchTopAlbums(artist)
        findNavController().navigate(action)
    }
    //endregion

}