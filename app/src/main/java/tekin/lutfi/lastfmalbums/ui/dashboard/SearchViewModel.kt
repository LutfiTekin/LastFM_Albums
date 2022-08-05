package tekin.lutfi.lastfmalbums.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.use_case.search.SearchUseCase
import tekin.lutfi.lastfmalbums.ui.home.AlbumUIState
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase): ViewModel() {

    private val _searchState = MutableStateFlow(AlbumUIState(list = emptyList()))

    val searchState: StateFlow<AlbumUIState> = _searchState

    fun runSearch(query: String) {
        searchUseCase(query).onEach { resource ->
            when (resource) {
                is Resource.Success ->
                    _searchState.value = AlbumUIState(false,resource.data ?: emptyList())
                is Resource.Loading -> _searchState.value = AlbumUIState(true)
                is Resource.Error -> _searchState.value = AlbumUIState(false,null,resource.message)
            }

        }.launchIn(viewModelScope)
    }


}