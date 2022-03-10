package com.app.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.app.newsapp.databinding.ActivityDetailsBinding
import com.app.newsapp.network.Resource
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.response.Articles
import com.app.newsapp.response.Source
import com.app.newsapp.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import org.json.JSONObject


class DetailsActivtiy :
    BaseActivity<HomeViewModel, ActivityDetailsBinding, HomeRepository>(),
    View.OnClickListener {

    override fun getViewModel() = HomeViewModel::class.java

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityDetailsBinding.inflate(layoutInflater)

    override fun getActivityRepository() = HomeRepository(
        remoteDataSource.buildApi(WebServiceAPI::class.java),
        preferenceUtils
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
        newsListResponse()
    }

    private fun initList() {
        viewModel.newsList()
    }
    private fun newsListResponse() {

        viewModel.newsListResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    progressbar.visibility = View.GONE

                    val articlesList: ArrayList<Articles> = ArrayList()
                    val sourceList: ArrayList<Source> = ArrayList()
                    articlesList.clear()
                    sourceList.clear()

                    articlesList.addAll(it.value.articles)

                    for (i in 0 until articlesList.size) {
                        sourceList.add(articlesList.get(i).source)
                    }
                    //ivNews
                    Glide.with(this)
                        .load(articlesList.get(0).urlToImage)
                        .into(ivNews)

                    tvDesc.text = articlesList.get(0).description
                }
                is Resource.Failure -> {
                    progressbar.visibility = View.GONE
                    val jObjError = JSONObject(it.errorBody!!.string())
                    logError(
                        "company api error",
                        jObjError.getString("message")
                    )
                    if (it.isNetworkError) {
                        showSnackBar(
                            resources.getString(R.string.internet_connection_error),
                            binding.clMain
                        )
                    } else {
                        showSnackBar(
                            jObjError.getString("message"),
                            binding.clMain
                        )
                    }
                }
            }
        })
    }


    override fun onClick(p0: View?) {
        when {
//            p0!!.id == R.id.tvBack -> {
//                finish()
//            }
        }

    }
}