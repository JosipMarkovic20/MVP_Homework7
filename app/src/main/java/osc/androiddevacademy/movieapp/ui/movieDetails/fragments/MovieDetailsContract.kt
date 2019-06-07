package osc.androiddevacademy.movieapp.ui.movieDetails.fragments

import osc.androiddevacademy.movieapp.model.Movie
import osc.androiddevacademy.movieapp.model.Review

interface MovieDetailsContract {

    interface View{

        fun onGetReview(reviews: List<Review>)

        fun onGetMovie(movie: Movie)

        fun onGetMoviesFailed()

    }

    interface Presenter{

        fun setView(view: View)

        fun onGetReview(movie: Movie)

    }
}