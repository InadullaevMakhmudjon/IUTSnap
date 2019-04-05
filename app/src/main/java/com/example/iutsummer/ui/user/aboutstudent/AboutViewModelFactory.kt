package com.example.iutsummer.ui.user.aboutstudent

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.iutsummer.App

class AboutViewModelFactory(private val app:App): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AboutStudentViewModel(app) as T
    }

}