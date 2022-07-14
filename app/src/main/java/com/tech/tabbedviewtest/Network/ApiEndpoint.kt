package com.tech.umr.Network

import com.tech.tabbedviewtest.NewsModelReponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiEndpoint {

    @GET("news")
    suspend fun getNewsList(@Query("access_key") access_key: String): Response<NewsModelReponse>

    @GET("news")
    fun getSourceList(
        @Query("access_key") access_key: String,
        @Query("search") search: String
    ): Response<NewsModelReponse>

    @GET("news")
    fun getCategoryList(@Query("access_key") access_key: String): Response<NewsModelReponse>




}