package com.app.newsapp.repository

import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.utils.PreferenceUtils

class HomeRepository(
    private val api: WebServiceAPI,
    private val preferences: PreferenceUtils
) : BaseRepository() {


    suspend fun newsList(
    ) = safeApiCall {
        api.newsList()
    }
}