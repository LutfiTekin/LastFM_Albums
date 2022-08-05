package tekin.lutfi.lastfmalbums.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.databinding.FragmentSearchBinding
import tekin.lutfi.lastfmalbums.ui.home.AlbumUIState

@AndroidEntryPoint
class SearchFragment : Fragment() {

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

    private val searchViewModel: SearchViewModel by viewModels()

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

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                searchViewModel.searchState.collect { state ->
                    if (state.isLoading) {
                        //show loading
                        Log.d("UISTATE","is loading")
                    }else {
                        if (state.error.isNullOrBlank()) {
                            //show error
                            Log.d("UISTATE", state.list.toString())
                        }else {
                            //Loading list
                            Log.d("UISTATE", state.error.orEmpty())
                        }
                    }
                }
            }
        }
    }

    private fun FragmentSearchBinding.setupUI(){
        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            searchViewModel.runSearch(query)
            Log.d("UISTATE","Query: $query")
        }
    }

}