package tekin.lutfi.lastfmalbums.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import tekin.lutfi.lastfmalbums.domain.model.Album
import tekin.lutfi.lastfmalbums.domain.use_case.local_albums.LocalAlbumsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val localAlbumsUseCase: LocalAlbumsUseCase): ViewModel() {

    fun getAlbums(): Flow<List<Album>> = localAlbumsUseCase.loadAlbums()

}