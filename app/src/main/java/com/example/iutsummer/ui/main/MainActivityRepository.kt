package com.example.iutsummer.ui.main

import android.arch.lifecycle.MutableLiveData

class MainActivityRepository {


    val layoutalignment = MutableLiveData<Boolean>()

    init {
        layoutalignment.value = true
    }


}