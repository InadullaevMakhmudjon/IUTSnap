package com.example.iutsummer.ui.main

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.iutsummer.databinding.ActivityMainBinding

class MainViewModelFactory(private val context:Application,private val binding: ActivityMainBinding):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainaActivityViewModel(context,binding) as T
    }
}