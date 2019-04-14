package com.example.iutsummer

import android.app.Application
import com.example.iutsummer.di.AppModule

class App: Application() {

    val appMode:AppModule
        get() = AppModule(this)

    override fun onCreate() {
        super.onCreate()
    }


}