package com.example.iutsummer.ui.user.lostfound

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.iutsummer.App

class LostFoundVMF(val app:App,val context:LostFoundFragment): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LostFoundVM(app,context) as T
    }
}