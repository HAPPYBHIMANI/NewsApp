package com.app.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsapp.network.Resource
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.repository.MainRepository
import com.app.newsapp.response.NewsListResponse
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: HomeRepository
) : ViewModel() {

    private val _newsListResponse: MutableLiveData<Resource<NewsListResponse>> =
        MutableLiveData()
    val newsListResponse: LiveData<Resource<NewsListResponse>>
        get() = _newsListResponse


    //NewsList
    fun newsList(
    ) = viewModelScope.launch {
        _newsListResponse.value = Resource.Loading
        _newsListResponse.value =
            repository.newsList()
    }

}