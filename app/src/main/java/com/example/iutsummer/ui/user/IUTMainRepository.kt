package com.example.iutsummer.ui.user

import com.example.iutsummer.App
import com.google.firebase.auth.FirebaseAuth

class IUTMainRepository(private val app:App) {
    private val database = app.appMode.database.getUserDao
    private val auth = FirebaseAuth.getInstance()
    var userrr = auth.currentUser

    fun dropStudent(){
        database.drop()
        userrr?.delete()
    }
}