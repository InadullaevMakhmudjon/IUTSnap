package com.example.iutsummer.ui.user.requestform

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.iutsummer.App
import com.example.iutsummer.databinding.FragmentRequestFormBinding

class ViewModelFactory(val ctx: App,val binding:FragmentRequestFormBinding) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RequestViewModel(ctx,binding) as T
    }
}