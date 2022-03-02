package com.app.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.app.newsapp.repository.MainRepository

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

}