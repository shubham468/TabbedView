package com.tech.tabbedviewtest

import com.tech.umr.Network.ApiEndpoint
import retrofit2.Response

class Repo(private val retroInstance: ApiEndpoint) {

    suspend fun getNewsList(key:String): Response<NewsModelReponse> = retroInstance.getNewsList(key)

}