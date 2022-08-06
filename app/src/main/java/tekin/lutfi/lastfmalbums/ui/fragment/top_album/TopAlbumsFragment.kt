package tekin.lutfi.lastfmalbums.ui.fragment.top_album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tekin.lutfi.lastfmalbums.R

class TopAlbumsFragment : Fragment() {

    companion object {
        fun newInstance() = TopAlbumsFragment()
    }

    private lateinit var viewModel: TopAlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopAlbumsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}