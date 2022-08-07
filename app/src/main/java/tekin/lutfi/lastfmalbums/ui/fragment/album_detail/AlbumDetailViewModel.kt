package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.use_case.local_albums.LocalAlbumsUseCase
import tekin.lutfi.lastfmalbums.domain.use_case.top_albums.TopAlbumUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val topAlbumsUseCase: TopAlbumUseCase,
    private val localAlbumsUseCase: LocalAlbumsUseCase
) : ViewModel() {
    private val _albumInfoState = MutableStateFlow(UIState<Album?>(data = null))

    val albumInfoState: StateFlow<UIState<Album?>> = _albumInfoState

    private val _favoriteButtonState = MutableStateFlow(UIState<Boolean?>(data = null))

    val favoriteButtonState = _favoriteButtonState

    fun loadAlbumTracks(album: Album) {
        //Skip network request if album is coming from local data
        if (album.tracks.isNotEmpty()){
            _albumInfoState.value = UIState(data = album)
            return
        }
        viewModelScope.launch {
            topAlbumsUseCase.getAlbumInfo(album.artist.orEmpty(), album.name.orEmpty()).collect { resource ->
                when (resource) {
                    is Resource.Success -> _albumInfoState.value = UIState(data = resource.data)
                    is Resource.Loading -> _albumInfoState.value = UIState(true)
                    is Resource.Error -> _albumInfoState.value =
                        UIState(error = resource.e.localizedMessage)
                }
            }
        }
    }

    fun isAlbumFavorited(albumName: String?){
        viewModelScope.launch {
            localAlbumsUseCase.isFavorited(albumName.orEmpty()).collectLatest { isFavorited ->
                _favoriteButtonState.value = UIState(data = isFavorited)
            }
        }
    }

    fun addAlbumToFavorites(album: Album){
        viewModelScope.launch {
            localAlbumsUseCase.addAlbum(album)
        }
    }

    fun removeAlbumFromFavorites(album: Album){
        viewModelScope.launch {
            localAlbumsUseCase.removeAlbum(album)
        }
    }

}