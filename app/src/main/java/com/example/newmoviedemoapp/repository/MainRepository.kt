package com.example.newmoviedemoapp.repository

import com.example.newmoviedemoapp.api.RetrofitClient

class MainRepository constructor(private val retrofitClient: RetrofitClient)  {

    fun getAllMovies() = retrofitClient.apiInterface.getAllMovies()

    fun getMovieDeatils(id: String, api_key : String) = retrofitClient.apiInterface.getMovieDetails(id ,api_key)
}