package com.example.iutsummer.ui.user

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.iutsummer.databinding.ActivityIutmainBinding

class IUTMainViewModelFactory(private val app:Application) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IUTMainViewModel(app) as T
    }
}