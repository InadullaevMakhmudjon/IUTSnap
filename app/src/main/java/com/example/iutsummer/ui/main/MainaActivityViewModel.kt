package com.example.iutsummer.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.iutsummer.utils.ObservableViewModel

class MainaActivityViewModel(appContext:Application): ObservableViewModel(appContext) {

   val repo = MainActivityRepository()

    val relativeLayoutAlignment:LiveData<Boolean>


    init {
        relativeLayoutAlignment = repo.layoutalignment
    }

}