package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridTopRatedContract {

    interface View{

        fun onMovieListRecieved(movies: MutableList<Movie>)

        fun onGetMoviesFailed()

    }

    interface Presenter{

        fun setView(view: View)

        fun onGetTopRatedMovies()

    }

}