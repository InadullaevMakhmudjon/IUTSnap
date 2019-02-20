package com.example.iutsummer.di

import android.arch.persistence.room.Room
import com.example.iutsummer.App
import com.example.iutsummer.data.db.AppDb

class AppModule(private val application:App){

    //Application
    var app:App = application

    //Room database
    var database:AppDb = Room.databaseBuilder(app,AppDb::class.java,"IUTDb").fallbackToDestructiveMigration().allowMainThreadQueries().build()



}