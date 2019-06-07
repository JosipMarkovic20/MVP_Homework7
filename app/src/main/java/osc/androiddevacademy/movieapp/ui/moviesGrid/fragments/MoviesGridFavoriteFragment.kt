package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragemnt_movie_grid.*
import kotlinx.android.synthetic.main.fragment_favourite.*
import osc.androiddevacademy.movieapp.App
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.common.displayToast
import osc.androiddevacademy.movieapp.common.showFragment
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.presentation.MoviesGridFavoritePresenter
import osc.androiddevacademy.movieapp.ui.movieDetails.fragments.MoviesPagerFragment
import osc.androiddevacademy.movieapp.ui.moviesGrid.adapters.MoviesGridAdapter

class MoviesGridFavoriteFragment : Fragment(), MoviesGridFavoriteContract.View {

    private val SPAN_COUNT = 2

    private val gridAdapter by lazy {
        MoviesGridAdapter(
            { onMovieClicked(it) },
            { onFavoriteClicked(it) })
    }
    private val appDatabase by lazy { MoviesDatabase.getInstance(App.getAppContext()) }
    private var movieList: ArrayList<Movie> = arrayListOf()

    private val presenter: MoviesGridFavoriteContract.Presenter by lazy {
        MoviesGridFavoritePresenter(appDatabase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteMovies.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }
        presenter.setView(this)
        getFavorites()
    }



    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        getFavorites()
    }

    private fun getFavorites() {
        presenter.onGetFavoriteMovies()
    }

    override fun onMovieListRecieved(movies: List<Movie>) {
        gridAdapter.setMovies(movies)
    }

    override fun movieListEmpty() {
        activity?.displayToast(getString(R.string.no_favs))
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
        if (movie.isFavorite) {
            appDatabase.moviesDao().deleteFavoriteMovie(movie)
            activity?.displayToast(getString(R.string.Remove_fav))
        }else{
            appDatabase.moviesDao().addFavoriteMovie(movie)
            activity?.displayToast(getString(R.string.Add_fav))
        }
    }

}