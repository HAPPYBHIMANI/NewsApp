package com.app.newsapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.newsapp.repository.BaseRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}