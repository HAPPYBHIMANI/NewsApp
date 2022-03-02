package com.app.newsapp.repository

import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.utils.PreferenceUtils


class MainRepository(
    private val api: WebServiceAPI,
    private val preferences: PreferenceUtils
) : BaseRepository() {
//    suspend fun getOrderlist(
//        vendor_id: String,
//    ) = safeApiCall {
//        api.getOrders(vendor_id)
//    }

}
