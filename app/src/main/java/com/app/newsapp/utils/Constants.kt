package com.app.newsapp.utils


object Constants {

    const val RESPONSE_SUCCESS: String = "200"

    interface ApiKEY {
        companion object {
            const val KEYWORD = "q"
            const val APIKEY = "apiKey"
        }
    }

    interface EndPoints {
        companion object {
            const val NEWS = "/v2/everything?q=keyword&apiKey=0efde78a14444bc4aa8acfbfe79c9120"
        }
    }
}