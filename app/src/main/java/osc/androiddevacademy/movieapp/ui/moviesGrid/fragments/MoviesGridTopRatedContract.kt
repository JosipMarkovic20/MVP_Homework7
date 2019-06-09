package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import osc.androiddevacademy.movieapp.database.MoviesDatabase
import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridTopRatedContract {

    interface View{

        fun onMovieListRecieved(movies: MutableList<Movie>)

        fun onGetMoviesFailed()

        fun favAdded()

        fun favRemoved()

    }

    interface Presenter{

        fun setView(view: View)

        fun onGetTopRatedMovies()

        fun onFavoriteClicked(movie: Movie, appDatabase: MoviesDatabase)

    }

}