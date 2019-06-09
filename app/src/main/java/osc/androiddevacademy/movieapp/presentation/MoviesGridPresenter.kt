package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridPresenter(private val interactor: MovieInteractor): MoviesGridContract.Presenter {


    private lateinit var view: MoviesGridContract.View

    override fun setView(view: MoviesGridContract.View) {
        this.view = view
    }

    override fun onGetPopularMovies(){
        interactor.getPopularMovies(popularMoviesCallback())
    }

    private fun popularMoviesCallback(): Callback<MoviesResponse> =
        object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                view.onGetMoviesFailed()
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.movies?.run {
                        view.onMovieListRecieved(this)
                    }
                }
            }
        }

    override fun onFavoriteClicked(movie: Movie, appDatabase: MoviesDatabase) {
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