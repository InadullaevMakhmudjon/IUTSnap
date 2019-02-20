package com.example.iutsummer.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import android.util.Log
import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

/**
 * 1. isSendEvent to UI update
 *
 */

class MainActivityRepository(private val application: App) {

    //region private properties
    /**
     * Triggers when verified
     *      1. returns null when user is not signed in
     *      2. returns false when user is not verified
     *      3. returns true when user verified
     */
    private val isVerified = MutableLiveData<Boolean>()

    private val userDao = application.appMode.database.getUserDao
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()
    var userrr = auth.currentUser

    //endregion

    //region public properties
    /**
     * Triggers UI when send message to student's email
     */
    var isSendEmail = MutableLiveData<Boolean>()

    /**
     * Triggers when user id correctly inputted
     */
    var isId = MutableLiveData<Boolean>()

    /**
     * Triggers when user email incorrectly inputted
     */
    var isEmail = MutableLiveData<Boolean>()

    /**
     * Listens db
     */
    val iutStudent:LiveData<Student> = userDao.getUser()

    //endregion

    //region public Methods

    //region Gets Student from Firebase
    /**
     * 1.Gets specific user from Firebase
     * 2.checks given email with student's emil
     * 3.if email correct then sends verification link
     */
    fun getUser(id:String?,email:String){
        var userListener: ValueEventListener = object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                var counterID=0
                var counterEmail=0
                p0.children.forEach {
                    val ID = it.child("ID").getValue(String::class.java)
                    val Name = it.child("Name").getValue(String::class.java)
                    if(ID!=null && Name!=null) {
                        if (ID == id) {
                            val student = Student()
                            student.ID = ID
                            student.Name=Name
                            counterID++
                            val fullEmail = getsFullEmail(student)
                                if(Objects.equals(email,fullEmail)) {
                                    sendEmailVerificationMessage(fullEmail,student)
                                    counterEmail++
                                }
                        }
                    }
                }
                isId.value = counterID != 0
                isEmail.value = counterEmail != 0
            }
        }
        database.addListenerForSingleValueEvent(userListener)
    }

    //endregion


    /**
     * Generates student's email
     */
    fun getsFullEmail(student:Student):String{
        val studentSurname = student.Name.substring(0,student.Name.lastIndexOf(" "))
        val extraToGetName = student.Name.substring(student.Name.indexOf(" ")+1)
        return "${extraToGetName[0].toLowerCase()}.${studentSurname.toLowerCase()}@student.inha.uz"
    }

    /**
     * Clear All students who entered to app
     */
    fun dropStudent(){
        application.appMode.database.getUserDao.drop()
        userrr?.delete()
    }

    //region Student Email Verification
    /**
     * Sends email verification link to user's email
     */
    fun sendEmailVerificationMessage(email:String,student:Student){
        auth.createUserWithEmailAndPassword(email,"1234567").addOnCompleteListener {
            auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                isSendEmail.value=true
                userrr=auth.currentUser
                loop(email,student)
            }
        }
    }


    /**
     * Checks whether email verified and only called by the loop() function to listen
     */
    fun signInWithEmail(email:String,student:Student){
        auth.signInWithEmailAndPassword(email,"1234567").addOnCompleteListener{
            if(userrr!=null) {
               if (userrr!!.isEmailVerified) {
                       userDao.insert(student)
                       isVerified.value = true
                }
            }
        }
    }


    /**
     * To check every 2 seconds whether verified
     */
    fun loop(email:String,student:Student){
        val handler = Handler()
        val timer = Timer()
        val doAsynchronousTask = object : TimerTask() {
            override fun run() {
                handler.post {
                    try {
                        signInWithEmail(email,student)
                    } catch (e: Exception) {
                    }
                }
            }
        }
        timer.schedule(doAsynchronousTask, 0, 2000)
    }
    //endregion

    //endregion
}