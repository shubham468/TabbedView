package com.tech.tabbedviewtest

class SourceModelReponse(
    val data: List<SourceDetails>
) {
}

class SourceDetails(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String,
    val category: String
) {

}
