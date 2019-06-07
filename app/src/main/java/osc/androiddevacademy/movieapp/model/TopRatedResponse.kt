package osc.androiddevacademy.movieapp.model

import com.google.gson.annotations.SerializedName

class TopRatedResponse(@SerializedName("results") val movies: ArrayList<Movie>)