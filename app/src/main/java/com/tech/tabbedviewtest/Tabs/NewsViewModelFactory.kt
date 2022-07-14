package com.tech.tabbedviewtest.Tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tech.tabbedviewtest.Repo

class NewsViewModelFactory(private val repository: Repo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            NewsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}