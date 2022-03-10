package com.app.newsapp.fragments

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.app.newsapp.DetailsActivtiy
import com.app.newsapp.R
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.databinding.FragmentHomeBinding
import com.app.newsapp.network.Resource
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.response.Articles
import com.app.newsapp.response.ArticlesData
import com.app.newsapp.response.Source
import com.app.newsapp.utils.AppDatabase
//import com.app.newsapp.utils.ArticleDatabase
import com.app.newsapp.utils.User
import com.app.newsapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerviewNews
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


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
//        callDatabase()

    }

    private fun callDatabase() {
        //getdata from table
        AsyncTask.execute {
            val users = AppDatabase.getDatabase(requireContext().getApplicationContext())!!
                .userDao()!!.all
            for (user in users) {
                Log.d("User", user.debugString)
            }
        }

        //insert data
        AsyncTask.execute {
            val date = Date()
            val user = User(date.getTime(), "Ng Wai Foong")
            AppDatabase.getDatabase(requireContext().getApplicationContext())!!.userDao()!!
                .insertAll(user)
        }

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

                    val adapter = NewsAdapter(requireContext(), articlesList)
                    binding.recyclerviewNews.adapter = adapter

                    newsAdapter =
                        NewsAdapter(requireContext(), articlesList)

                    recyclerviewNews!!.adapter = newsAdapter
                    newsAdapter!!.onItemClick =
                        { position: Int, isBookmarked: String,author:String ->
//                            Log.e("called", "adfsfse___")
//                            val tempList: ArrayList<Articles> = ArrayList()
//                            if (isBookmarked.equals("true")){
//                                tempList.add(articlesList.get(position))
//                            }else if (isBookmarked.equals("false")){
//                                tempList.remove(articlesList.get(position))
//                            }
//
//                            Log.e("called", "tempList___"+tempList.toString())
//                            Log.e("called", "tempList__size___"+tempList.size)


                            //insert data
                            AsyncTask.execute {
                                val date = Date()
                                val user = User(date.getTime(), "Ng Wai Foong")
                                val articlesData = ArticlesData(
//                                    articlesList.get(position).source,
                                    author,
//                                    articlesList.get(position).title,
                                    articlesList.get(position).description,
//                                    articlesList.get(position).url,
//                                    articlesList.get(position).urlToImage,
//                                    articlesList.get(position).publishedAt,
//                                    articlesList.get(position).content
                                )
//                                AppDatabase.getDatabase(requireContext().getApplicationContext())!!
//                                    .userDao()!!.insertAll(user)
//                                ArticleDatabase.getDatabase(requireContext().getApplicationContext())!!
//                                    .userDao()!!.insertArticles(articlesData)
                                AppDatabase.getDatabase(requireContext().getApplicationContext())!!
                                    .userDao()!!.insertArticles(articlesData)
                            }

                            //getData
                            AsyncTask.execute {
                                val articles = AppDatabase.getDatabase(requireContext().getApplicationContext())!!
                                    .userDao()!!.articles
                                for (art in articles) {
                                    Log.d("articles________", art.dataList)
                                }

                            }
//                            AsyncTask.execute {
//                                val users = AppDatabase.getDatabase(requireContext().getApplicationContext())!!
//                                    .userDao()!!.all
//                                for (user in users) {
//                                    Log.d("User", user.debugString)
//                                }
//                            }

                            val intent = Intent(requireContext(), DetailsActivtiy::class.java)
                            startActivity(intent)
                        }
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