package com.app.newsapp.network

import com.app.newsapp.response.NewsListResponse
import com.app.newsapp.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WebServiceAPI {

//    @FormUrlEncoded
//    @POST(Constants.EndPoints.NEWS)
//    suspend fun newsList(
//        @Field(Constants.ApiKEY.KEYWORD) keyword: String,
//        @Field(Constants.ApiKEY.APIKEY) apiKey: String,
//    ): NewsListResponse

    @GET(Constants.EndPoints.NEWS)
    suspend fun newsList(
//        @Field(Constants.ApiKEY.KEYWORD) keyword: String,
//        @Field(Constants.ApiKEY.APIKEY) apiKey: String,
    ): NewsListResponse


}