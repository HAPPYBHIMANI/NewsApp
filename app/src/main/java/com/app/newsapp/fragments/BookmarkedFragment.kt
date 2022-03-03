package com.app.newsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.newsapp.R
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.databinding.FragmentBookmarkedBinding
import com.app.newsapp.network.Resource
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.response.Articles
import com.app.newsapp.response.Source
import com.app.newsapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bookmarked.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.progressbar
import kotlinx.android.synthetic.main.fragment_home.recyclerviewNews
import org.json.JSONObject

class BookmarkedFragment :
    BaseFragment<HomeViewModel, FragmentBookmarkedBinding, HomeRepository>() {

    override fun getViewModel() = HomeViewModel::class.java
    private var newsAdapter: NewsAdapter? = null


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentBookmarkedBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        HomeRepository(remoteDataSource.buildApi(WebServiceAPI::class.java), userPreferences)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBookmarked()
        bookmarkedResponse()
    }

    private fun initBookmarked() {
        viewModel.newsList()
    }

    private fun bookmarkedResponse() {
        viewModel.newsListResponse.observe(requireActivity(), Observer {
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
                    recyclerviewBookmarked.adapter = NewsAdapter(requireContext(), articlesList)
                    newsAdapter = NewsAdapter(requireContext(), articlesList)
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
                            binding.recyclerviewBookmarked
                        )
                    } else {
                        showSnackBar(
                            jObjError.getString("message"),
                            recyclerviewBookmarked                        )
                    }
                }
            }
        })
    }


}