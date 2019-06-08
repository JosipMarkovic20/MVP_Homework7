package osc.androiddevacademy.movieapp.ui.movieDetails.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_pager.*
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.movieDetails.adapters.MoviePagerAdapter

class MoviesPagerFragment : Fragment() {

    companion object {
        private const val PAGER_LIST_EXTRA = "list_extra"
        private const val PAGER_SELECTED_MOVIE_EXTRA = "selected_movie"

        fun getInstance(movieList: ArrayList<Movie>, selectedMovie: Movie): MoviesPagerFragment {
            val pagerFragment =
                MoviesPagerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PAGER_LIST_EXTRA, movieList)
            bundle.putParcelable(PAGER_SELECTED_MOVIE_EXTRA, selectedMovie)
            pagerFragment.arguments = bundle
            return pagerFragment
        }
    }

    private val movieList = mutableListOf<Movie>()
    private val pagerAdapter by lazy {
        MoviePagerAdapter(
            childFragmentManager
        )
    }

    private fun checkId(movie: Movie): Int {
        for (x in 0 until movieList.size) {
            if (  movie.id == movieList[x].id) return x
        }
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieList.addAll(arguments?.getParcelableArrayList(PAGER_LIST_EXTRA)!!)

        moviePager.adapter = pagerAdapter

        val position = checkId(arguments!!.getParcelable(PAGER_SELECTED_MOVIE_EXTRA))

        pagerAdapter.setMovies(movieList)

        moviePager.setCurrentItem(position)
    }

}