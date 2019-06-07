package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.MoviesResponse
import osc.androiddevacademy.movieapp.model.TopRatedResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridContract
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridTopRatedContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesGridTopRatedPresenter(private val interactor: MovieInteractor): MoviesGridTopRatedContract.Presenter {


    private lateinit var view: MoviesGridTopRatedContract.View

    override fun setView(view: MoviesGridTopRatedContract.View) {
        this.view = view
    }

    override fun onGetTopRatedMovies(){
        interactor.getTopMovies(topRatedMoviesCallback())
    }

    private fun topRatedMoviesCallback(): Callback<TopRatedResponse> =
        object : Callback<TopRatedResponse> {
            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                view.onGetMoviesFailed()
            }

            override fun onResponse(
                call: Call<TopRatedResponse>,
                response: Response<TopRatedResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.movies?.run {
                        view.onMovieListRecieved(this)
                    }
                }
            }
        }
}