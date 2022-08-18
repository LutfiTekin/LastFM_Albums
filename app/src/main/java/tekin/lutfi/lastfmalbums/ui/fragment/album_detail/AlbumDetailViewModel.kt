package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.use_case.albums.AlbumUseCase
import tekin.lutfi.lastfmalbums.ui.UIState
import tekin.lutfi.lastfmalbums.utils.Resource


class AlbumDetailViewModel @AssistedInject constructor(
    @Assisted private val album: Album,
    private val albumsUseCase: AlbumUseCase
) : ViewModel() {

    @AssistedFactory
    interface AlbumDetailAssistedFactory {
        fun create(album: Album): AlbumDetailViewModel
    }

    private val _album = MutableStateFlow<Album?>(null)

    val favoriteButtonState = _album.filterNotNull().flatMapLatest { album ->
        albumsUseCase.isFavouriteState(album).map {
            UIState<Boolean?>(data = it)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState(data = null))


    val albumInfoState = _album.filterNotNull().flatMapLatest { album ->
        albumsUseCase.getAlbum(album.artist.orEmpty(), album.name.orEmpty()).map { resource ->
            when (resource) {
                is Resource.Success -> UIState(data = resource.data)
                is Resource.Loading -> UIState(true)
                is Resource.Error -> UIState(error = resource.e.localizedMessage)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState(isLoading = true))


    fun addAlbumToFavorites(album: Album){
        viewModelScope.launch {
            albumsUseCase.addAlbum(album)
        }
    }

    fun removeAlbumFromFavorites(album: Album){
        viewModelScope.launch {
            albumsUseCase.removeAlbum(album)
        }
    }

    init {
        _album.value = album
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun providesFactory(
            assistedFactory: AlbumDetailAssistedFactory,
            album: Album
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return assistedFactory.create(album) as T
            }
        }
    }

}