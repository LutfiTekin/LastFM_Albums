package tekin.lutfi.lastfmalbums.ui.fragment.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.use_case.albums.AlbumUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val albumsUseCase: AlbumUseCase): ViewModel() {

    fun getAlbums(): Flow<List<Album>> = albumsUseCase.loadAlbums()

}