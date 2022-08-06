package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.use_case.top_albums.TopAlbumUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val topAlbumsUseCase: TopAlbumUseCase): ViewModel() {
    private val _albumInfoState = MutableStateFlow(UIState<Album?>(data = null))

    val albumInfoState: StateFlow<UIState<Album?>> = _albumInfoState

    fun loadAlbumTracks(artist: String, album: String) {
        viewModelScope.launch {
            topAlbumsUseCase.getAlbumInfo(artist, album).collect { resource ->
                when (resource) {
                    is Resource.Success -> _albumInfoState.value = UIState(data = resource.data)
                    is Resource.Loading -> _albumInfoState.value = UIState(true)
                    is Resource.Error -> _albumInfoState.value = UIState(error = resource.e.message)
                }
            }
        }
    }
}