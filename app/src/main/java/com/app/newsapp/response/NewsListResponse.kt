package com.app.newsapp.response


//class NewsListResponse : CommonResponseModel() {
//}

data class NewsListResponse (
    val articles: List<Articles>,
    val status: String,
    val totalResults: String
)
