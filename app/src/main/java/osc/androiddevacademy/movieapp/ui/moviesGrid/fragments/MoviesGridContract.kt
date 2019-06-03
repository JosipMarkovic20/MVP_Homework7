package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridContract {

    interface View{

        fun onMovieListRecieved(tasks: MutableList<Movie>)

        fun onGetMoviesFailed()

    }

    interface Presenter{

        fun setView(view: MoviesGridContract.View)

        fun onGetPopularMovies(): ArrayList<Movie>

    }

}