package com.example.iutsummer.ui.user

import com.example.iutsummer.App
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class IUTMainRepository(private val app:App) {
    private val database = app.appMode.database.getUserDao
    private val auth = FirebaseAuth.getInstance()
    var userrr = auth.currentUser

    /**
     * Gets user from databse
     */
    val student = database.getUser()

    /**
     * Removes current user from firebase account
     */
    fun dropStudent(){
        val email = student.value?.email
        if(email!=null) {
            val credential = EmailAuthProvider.getCredential(email, "1234567")
            userrr!!.reauthenticate(credential).addOnCompleteListener {
                userrr!!.delete().addOnCompleteListener {
                    database.drop()
                }
            }
        }
    }
}