package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.presentation.MoviesGridPresenter
import osc.androiddevacademy.movieapp.ui.movieDetails.fragments.MoviesPagerFragment
import osc.androiddevacademy.movieapp.ui.moviesGrid.adapters.MoviesGridAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridFragment : Fragment() {

    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }
    private val apiInteractor: MovieInteractor by lazy { BackendFactory.getMovieInteractor() }
    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }
    private var movieList: ArrayList<Movie> = arrayListOf()

    private val presenter: MoviesGridContract.Presenter by lazy {
        MoviesGridPresenter(apiInteractor,gridAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragemnt_movie_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }

        requestPopularMovies()
    }

    override fun onResume() {
        super.onResume()
        requestPopularMovies()
    }

    private fun requestPopularMovies() {
        movieList = presenter.onGetPopularMovies()
    }



    private fun onMovieClicked(movie: Movie) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(
                movieList,
                movie
            ),
            true
        )
    }

    private fun onFavoriteClicked(movie: Movie) {

    }

}