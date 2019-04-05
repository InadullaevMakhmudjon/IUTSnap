package com.example.iutsummer.ui.user.lostfound.dialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.widget.ImageView
import com.example.iutsummer.App
import com.example.iutsummer.ui.user.lostfound.LostFoundFragment

class DialogLFVMFactory(val app:App, val parent: LostFoundFragment,val img:ImageView): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DialogLFViewModel(app, parent,img) as T
    }
}