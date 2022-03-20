package com.example.newmoviedemoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newmoviedemoapp.model.MovieDeatilsResponse
import com.example.newmoviedemoapp.model.MovieResponse
import com.example.newmoviedemoapp.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<MovieResponse>()
    val errorMessage = MutableLiveData<String>()


    val movieDetailsResult = MutableLiveData<MovieDeatilsResponse>()
    val movieErrorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response: Call<MovieResponse> = repository.getAllMovies()

        response.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movieList.postValue(response.body())
                Log.e("Moviee", "onFailure: Sucess  message -> ${response.body()?.results?.size}")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.e("Moviee", "onFailure: Error message -> ${t.message}")
            }

        })
    }


    fun getMovieDeatild(movieId: String, api_key: String) {
        val response: Call<MovieDeatilsResponse?>? = repository.getMovieDeatils(movieId, api_key)

        response?.enqueue(object : Callback<MovieDeatilsResponse?> {
            override fun onResponse(
                call: Call<MovieDeatilsResponse?>,
                response: Response<MovieDeatilsResponse?>
            ) {

                movieDetailsResult.postValue(response.body())
                Log.e(
                    "Moviee",
                    "onResponse: Details Response   message -> ${response.body()?.results?.size}"
                )

            }

            override fun onFailure(call: Call<MovieDeatilsResponse?>, t: Throwable) {
                movieErrorMessage.postValue(t.message)
                Log.e("Moviee", "onFailure: error   message -> ${t.message}")
            }

        })


    }


}