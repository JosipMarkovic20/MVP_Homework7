package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.moviesGrid.adapters.MoviesGridAdapter
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridPresenter(private val interactor: MovieInteractor, private val gridAdapter: MoviesGridAdapter): MoviesGridContract.Presenter {


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
}