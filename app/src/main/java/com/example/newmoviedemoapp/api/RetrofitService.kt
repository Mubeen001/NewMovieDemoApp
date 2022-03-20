package com.example.newmoviedemoapp.api

import com.example.newmoviedemoapp.model.MovieDeatilsResponse
import com.example.newmoviedemoapp.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {


    @POST("upcoming?api_key=fbedc1ea1afdea3cb9eeef92310dcbe6")
    fun getAllMovies(): Call<MovieResponse>


    @GET("{id}/videos")
    fun getMovieDetails(
        @Path("id") id: String?,
        @Query("api_key") api_key: String?
    ): Call<MovieDeatilsResponse?>?

}