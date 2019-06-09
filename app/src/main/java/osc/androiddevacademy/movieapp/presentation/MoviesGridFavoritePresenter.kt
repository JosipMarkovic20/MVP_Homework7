package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.common.displayToast
import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridFavoriteContract

class MoviesGridFavoritePresenter(private val appDatabase: MoviesDatabase): MoviesGridFavoriteContract.Presenter {

    private lateinit var view: MoviesGridFavoriteContract.View

    override fun setView(view: MoviesGridFavoriteContract.View) {
        this.view = view
    }

    override fun onGetFavoriteMovies() {
        val data = appDatabase.moviesDao().getFavoriteMovies()
        if(data.isNotEmpty()){
            view.onMovieListRecieved(data)
        }else{
            view.movieListEmpty()
        }
    }

    override fun onFavoriteClicked(movie: Movie) {
        val findMovie: Movie? = appDatabase.moviesDao().getMovie(movie.id)
        if(findMovie?.id==movie.id){
            appDatabase.moviesDao().deleteFavoriteMovie(findMovie)
            view.favRemoved()
        }else{
            appDatabase.moviesDao().addFavoriteMovie(movie)
            view.favAdded()
        }
    }
}