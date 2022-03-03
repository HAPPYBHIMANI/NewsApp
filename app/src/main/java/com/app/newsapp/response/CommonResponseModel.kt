package com.app.newsapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class CommonResponseModel {

    @SerializedName("status")
    @Expose
    var status: String? = null
//
//    @SerializedName("message")
//    @Expose
//    var message: String? = null

}