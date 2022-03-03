package com.app.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.newsapp.R
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.databinding.FragmentHomeBinding
import com.app.newsapp.network.Resource
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.response.Articles
import com.app.newsapp.response.Source
import com.app.newsapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerviewNews
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding, HomeRepository>() {

    private var newsAdapter: NewsAdapter? = null

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)


    override fun getFragmentRepository() =
        HomeRepository(remoteDataSource.buildApi(WebServiceAPI::class.java), userPreferences)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        newsListResponse()
    }

    private fun initList() {
        viewModel.newsList()
    }


    private fun newsListResponse() {
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
                    recyclerviewNews.adapter = NewsAdapter(requireContext(), articlesList)
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
                            binding.recyclerviewNews
                        )
                    } else {
                        showSnackBar(
                            jObjError.getString("message"),
                            binding.recyclerviewNews
                        )
                    }
                }
            }
        })
    }


}