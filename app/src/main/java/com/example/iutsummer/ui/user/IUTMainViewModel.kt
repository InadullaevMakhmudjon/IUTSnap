package com.example.iutsummer.ui.user

import android.app.Application
import com.example.iutsummer.utils.ObservableViewModel

class IUTMainViewModel(val app: Application): ObservableViewModel(app) {
    private val reopsitory = IUTMainRepository()
}