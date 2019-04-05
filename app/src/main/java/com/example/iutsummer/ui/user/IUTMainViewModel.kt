package com.example.iutsummer.ui.user

import android.app.Application
import android.app.DatePickerDialog
import android.arch.lifecycle.MutableLiveData
import com.example.iutsummer.App
import com.example.iutsummer.databinding.ActivityIutmainBinding
import com.example.iutsummer.utils.ObservableViewModel
import com.example.iutsummer.utils.SelectedDate

class IUTMainViewModel(val app: Application): ObservableViewModel(app) {
    private val repository = IUTMainRepository(app as App)

    var isLoading = MutableLiveData<Boolean>()

    /**
     * Gets student from repo
     */
    fun student() = repository.student

    /**
     * When user log out it clears all users from database
     */
    fun LogOut(){
        isLoading.value=true
        repository.dropStudent()
    }

    init {
        isLoading.value=false
    }

}