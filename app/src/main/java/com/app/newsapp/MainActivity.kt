package com.app.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.app.newsapp.databinding.ActivityMainBinding
import com.app.newsapp.network.WebServiceAPI
import com.app.newsapp.repository.MainRepository
import com.app.newsapp.viewmodel.MainViewModel


class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding, MainRepository>(),
    View.OnClickListener {

//    private var dashboardDetailsAdapter: DashboardDetailsAdapter? = null

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

//        initOrderDetail()
//        orderDetailResponse()

    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
//            R.id.tvBack -> onBackPressed()
        }
    }


}
