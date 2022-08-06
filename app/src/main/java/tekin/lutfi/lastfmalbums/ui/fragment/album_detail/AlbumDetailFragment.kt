package tekin.lutfi.lastfmalbums.ui.fragment.album_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tekin.lutfi.lastfmalbums.R

class AlbumDetailFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumDetailFragment()
    }

    private lateinit var viewModel: TopAlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopAlbumsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}