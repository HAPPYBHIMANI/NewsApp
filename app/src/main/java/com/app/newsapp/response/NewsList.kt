package com.app.newsapp.response

data class Articles(
    val source:Source,
//    val source:List<Source>,
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val publishedAt:String,
    val content:String,
)