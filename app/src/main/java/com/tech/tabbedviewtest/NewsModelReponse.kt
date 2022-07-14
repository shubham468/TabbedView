package com.tech.tabbedviewtest

import com.google.gson.annotations.SerializedName

class NewsModelReponse(
    @SerializedName("data")
    val data: List<NewsDetails>
) {
}

class NewsDetails(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String,
    val category: String
) {

}
