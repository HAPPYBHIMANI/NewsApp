package com.app.newsapp.network

import com.app.newsapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://newsapi.org"

        var termsUrl: String = BASE_URL + "term"
        var privacyUrl: String = BASE_URL + "privacy"
        var faqUrl: String = BASE_URL + "faq"

    }

    var token = ""

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor { chain ->
                        /* var preferenceUtils: PreferenceUtils = PreferenceUtils(BaseFragment.baseFragment)
                         try {
                             token = preferenceUtils.getString(Constants.PreferenceConstant.FCM_TOKEN)
                         } catch (e:Exception){
                             token = ""
                         }*/

                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("X_Api_Key", "qiqhyah5kBu9XdyNa1KERAMVhxR")
                            // it.addHeader("fcm-token", token)
                            it.addHeader("Content-Type", "application/json")
                            it.addHeader("device_type", "android")
                            it.addHeader("version", "1.0")
                            it.addHeader("lang", "en")
                            it.addHeader(
                                "device_token", "123456"
                            )
                        }.build())
                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}