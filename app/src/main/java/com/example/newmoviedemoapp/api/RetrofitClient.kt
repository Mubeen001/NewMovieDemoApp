package com.example.newmoviedemoapp.api

import com.example.newmoviedemoapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {

    private val BOOK_SEARCH_SERVICE_BASE_URL = "https://api.themoviedb.org/3/movie/"

    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: HttpLoggingInterceptor.Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY else levelType = HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        okhttpClient.addInterceptor(logging)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    val apiInterface : RetrofitService by lazy {
        retrofitClient
            .build()
            .create(RetrofitService::class.java)
    }

}