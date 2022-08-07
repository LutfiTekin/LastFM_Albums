package tekin.lutfi.lastfmalbums.ui.fragment.top_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.TopAlbum
import tekin.lutfi.lastfmalbums.domain.model.album
import tekin.lutfi.lastfmalbums.domain.use_case.albums.AlbumUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource

import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(
    private val albumsUseCase: AlbumUseCase
) :
    ViewModel() {

    private val _topAlbumState = MutableStateFlow(UIState<List<TopAlbum>?>(data = null))

    val topAlbumState: StateFlow<UIState<List<TopAlbum>?>> = _topAlbumState

    fun loadTopAlbums(artist: String) {
        viewModelScope.launch {
            albumsUseCase.getTopAlbums(artist).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val list = resource.data ?: emptyList()
                        applyFavorites(list)
                        _topAlbumState.value = UIState(data = list)
                    }
                    is Resource.Loading -> _topAlbumState.value = UIState(true)
                    is Resource.Error -> _topAlbumState.value =
                        UIState(error = resource.e.localizedMessage)
                }
            }
        }
    }

    private suspend fun applyFavorites(topAlbums: List<TopAlbum>) {
        topAlbums.forEach {
            it.isFavorite = isFavorite(it)
        }
    }

    suspend fun isFavorite(topAlbum: TopAlbum) = albumsUseCase.localAlbumExists(topAlbum.artist.orEmpty(), topAlbum.name.orEmpty())

    fun setFavorite(topAlbum: TopAlbum, stateChanged: (Boolean) -> Unit) = viewModelScope.launch {
        if (topAlbum.isFavorite) {
            albumsUseCase.getAlbumInfo(topAlbum.artist.orEmpty(), topAlbum.name.orEmpty())
                .collect { resource ->
                    if (resource is Resource.Success){
                        val album = resource.data ?: return@collect
                        albumsUseCase.addAlbum(album = album)
                        stateChanged(true)
                    }
                }
        } else {
            albumsUseCase.removeAlbum(topAlbum.album)
            stateChanged(false)
        }
    }

}