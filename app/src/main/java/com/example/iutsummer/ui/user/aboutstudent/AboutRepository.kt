package com.example.iutsummer.ui.user.aboutstudent

import com.example.iutsummer.App

class AboutRepository(private val app:App) {

    /**
     * Gets user from database
     */
    val student = app.appMode.database.getUserDao.getUser()

}