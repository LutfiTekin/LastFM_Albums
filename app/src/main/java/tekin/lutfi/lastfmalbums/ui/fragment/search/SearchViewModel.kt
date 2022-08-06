package tekin.lutfi.lastfmalbums.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Artist
import tekin.lutfi.lastfmalbums.domain.use_case.search.SearchUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase): ViewModel() {

    private val _searchState = MutableStateFlow(UIState<Artist>(list = emptyList()))

    val searchState: StateFlow<UIState<Artist>> = _searchState

    fun runSearch(query: String) {
        viewModelScope.launch {
            searchUseCase.searchArtist(query).collect { resource ->
                when (resource) {
                    is Resource.Success ->
                        _searchState.value = UIState(false,resource.data)
                    is Resource.Loading -> _searchState.value = UIState(true)
                    is Resource.Error -> _searchState.value = UIState(false,null,resource.e.message)
                }
            }
        }
    }


}