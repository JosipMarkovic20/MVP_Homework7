package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import osc.androiddevacademy.movieapp.model.Movie

interface MoviesGridFavoriteContract {

    interface View{

        fun onMovieListRecieved(movies: List<Movie>)

        fun movieListEmpty()

        fun favAdded()

        fun favRemoved()
    }

    interface Presenter{

        fun setView(view: View)

        fun onGetFavoriteMovies()

        fun onFavoriteClicked(movie: Movie)

    }

}