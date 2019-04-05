package com.example.iutsummer.ui.user.news

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.News
import com.example.iutsummer.utils.ObservableViewModel
import kotlin.math.log

class NewsViewModel(private val appk:Application): ObservableViewModel(appk) {

    private val repository = NewsRepository(appk as App)

    var allNews=repository.allNews

    fun initializeNews() = repository.initializeNews()

    fun deleteAllNews() = repository.deleteAllNews()

}