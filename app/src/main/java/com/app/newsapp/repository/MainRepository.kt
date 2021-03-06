package com.app.newsapp.repository

import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.utils.PreferenceUtils


class MainRepository(
    private val api: WebServiceAPI,
    private val preferences: PreferenceUtils
) : BaseRepository() {


}
