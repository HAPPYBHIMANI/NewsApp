package com.app.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.app.newsapp.adapter.NewsAdapter
import com.app.newsapp.databinding.ActivityMainBinding
import com.app.newsapp.network.Resource
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.MainRepository
import com.app.newsapp.response.Articles
import com.app.newsapp.response.Source
import com.app.newsapp.utils.RecyclerViewPaginator
import com.app.newsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding, MainRepository>(),
    View.OnClickListener {

    private var newsAdapter: NewsAdapter? = null
    var isLoading: Boolean = false

    override fun getViewModel() = MainViewModel::class.java

    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getActivityRepository() = MainRepository(
        remoteDataSource.buildApi(WebServiceAPI::class.java), preferenceUtils
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initList()
//        newsListResponse()
    }

//    private fun newsListResponse() {
//        viewModel.newsListResponse.observe(this, Observer {
//            when (it) {
//                is Resource.Success -> {
//                    val articlesList: ArrayList<Articles> = ArrayList()
//                    val sourceList: ArrayList<Source> = ArrayList()
//                    articlesList.clear()
//                    sourceList.clear()
//
//                    articlesList.addAll(it.value.articles)
//
//                    for (i in 0 until articlesList.size) {
//                        sourceList.add(articlesList.get(i).source)
//                    }
//                    recyclerviewNews.adapter = NewsAdapter(this, articlesList)
//                    newsAdapter = NewsAdapter(this, articlesList)
//                }
//                is Resource.Failure -> {
//                    val jObjError = JSONObject(it.errorBody!!.string())
//                    logError(
//                        "company api error",
//                        jObjError.getString("message")
//                    )
//                    if (it.isNetworkError) {
//                        showSnackBar(
//                            resources.getString(R.string.internet_connection_error),
//                            binding.recyclerviewNews
//                        )
//                    } else {
//                        showSnackBar(
//                            jObjError.getString("message"),
//                            binding.recyclerviewNews
//                        )
//                    }
//                }
//            }
//        })
//    }

//    private fun loadFirstPage() {
////        movieService.getMovies().enqueue(object : Callback<List<Movie?>?>() {
////            fun onResponse(call: Call<List<Movie?>?>?, response: Response<List<Movie?>?>) {
////                val results: List<Movie> = response.body()
////                progressBar.setVisibility(View.GONE)
////                paginationAdapter.addAll(results)
////                if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter() else isLastPage =
////                    true
////            }
////
////            fun onFailure(call: Call<List<Movie?>?>?, t: Throwable?) {}
////        })
//    }
//
//    private fun loadNextPage() {
////        movieService.getMovies().enqueue(object : Callback<List<Movie?>?>() {
////            fun onResponse(call: Call<List<Movie?>?>?, response: Response<List<Movie?>?>) {
////                paginationAdapter.removeLoadingFooter()
////                isLoading = false
////                val results: List<Movie> = response.body()
////                paginationAdapter.addAll(results)
////                if (currentPage !== TOTAL_PAGES) paginationAdapter.addLoadingFooter() else isLastPage =
////                    true
////            }
////
////            fun onFailure(call: Call<List<Movie?>?>?, t: Throwable) {
////                t.printStackTrace()
////            }
////        })
//    }
//
//
//    private fun initList() {
//        viewModel.newsList()
////        ////////
////        recyclerviewNews.addOnScrollListener(object :
////            RecyclerViewPaginator(recyclerviewNews) {
////            override val isLastPage: Boolean
////                //get() = viewModel.newsList()
////                get() = isLastPage
////
////            override fun loadMore(start: Long, count: Long) {
////                Log.e("called","loadMore__")
////                viewModel.newsList()
////            }
////        })
////        ////
//    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
//            R.id.tvBack -> onBackPressed()
        }
    }


}
