package com.tech.tabbedviewtest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APiEndpoint {

    companion object {

        var BASE_URL = "http://api.mediastack.com/v1/"

        fun create(): APiEndpoint {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APiEndpoint::class.java)

        }
    }

    @GET("news")
    fun getNewsList(@Query("access_key") access_key: String): Call<NewsModelReponse>

    @GET("news")
    fun getSourceList(
        @Query("access_key") access_key: String,
        @Query("search") search: String
    ): Call<NewsModelReponse>

    @GET("news")
    fun getCategoryList(@Query("access_key") access_key: String): Call<NewsModelReponse>


}