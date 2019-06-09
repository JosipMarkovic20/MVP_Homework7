package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.displayToast
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.networking.BackendFactory
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.presentation.MoviesGridTopRatedPresenter
import osc.androiddevacademy.movieapp.ui.movieDetails.fragments.MoviesPagerFragment
import osc.androiddevacademy.movieapp.ui.moviesGrid.adapters.MoviesGridAdapter

class MoviesGridTopRatedFragment : Fragment(), MoviesGridTopRatedContract.View {


    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }

    private val apiInteractor: MovieInteractor by lazy { BackendFactory.getMovieInteractor() }
    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }
    private var movieList: ArrayList<Movie> = arrayListOf()

    private val presenter: MoviesGridTopRatedContract.Presenter by lazy {
        MoviesGridTopRatedPresenter(apiInteractor)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedMovies.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }
        requestTopRatedMovies()
    }

    override fun onResume() {
        super.onResume()
        requestTopRatedMovies()
        presenter.setView(this)
    }

    override fun onMovieListRecieved(movies: MutableList<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        gridAdapter.setMovies(movieList)
    }

    override fun onGetMoviesFailed() {
        activity?.displayToast(getString(R.string.Error))
    }

    private fun requestTopRatedMovies() {
        presenter.onGetTopRatedMovies()
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

    override fun favAdded() {
        activity?.displayToast(getString(R.string.Add_fav))
    }

    override fun favRemoved() {
        activity?.displayToast(getString(R.string.Remove_fav))
    }

    private fun onFavoriteClicked(movie: Movie) {
       presenter.onFavoriteClicked(movie, appDatabase)
    }

}