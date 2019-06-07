package osc.androiddevacademy.movieapp.presentation

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.ReviewsResponse
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.ui.movieDetails.fragments.MovieDetailsContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsPresenter(private val apiInteractor: MovieInteractor): MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View

    override fun setView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun onGetReview(movie: Movie) {
        getReviews(movie)
    }

    private fun getReviews(movie: Movie){
        apiInteractor.getReviewsForMovie(movie.id, reviewsCallback())
    }

    private fun reviewsCallback(): Callback<ReviewsResponse> = object : Callback<ReviewsResponse> {
        override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
            if (response.isSuccessful){
                response.body()?.reviews?.run {
                    view.onGetReview(this)
                }
            }
        }
    }
}