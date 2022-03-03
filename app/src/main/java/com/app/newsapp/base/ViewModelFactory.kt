package com.app.newsapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.newsapp.repository.BaseRepository
import com.app.newsapp.repository.HomeRepository
import com.app.newsapp.repository.MainRepository
import com.app.newsapp.viewmodel.HomeViewModel
import com.app.newsapp.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                repository as MainRepository
            ) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as HomeRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}