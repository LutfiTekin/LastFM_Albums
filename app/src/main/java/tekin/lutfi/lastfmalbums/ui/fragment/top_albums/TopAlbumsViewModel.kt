package tekin.lutfi.lastfmalbums.ui.fragment.top_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.use_case.top_albums.TopAlbumUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource

import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(private val topAlbumsUseCase: TopAlbumUseCase) :
    ViewModel() {

    private val _topAlbumState = MutableStateFlow(UIState<TopAlbum?>(list = emptyList()))

    val topAlbumState: StateFlow<UIState<TopAlbum?>> = _topAlbumState

    fun loadTopAlbums(artist: String) {
        viewModelScope.launch {
            topAlbumsUseCase.getTopAlbums(artist).collect { resource ->
                when (resource) {
                    is Resource.Success -> _topAlbumState.value = UIState(list = resource.data)
                    is Resource.Loading -> _topAlbumState.value = UIState(true)
                    is Resource.Error -> _topAlbumState.value = UIState(error = resource.e.message)
                }
            }
        }
    }

}