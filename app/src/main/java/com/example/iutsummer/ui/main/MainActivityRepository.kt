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
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Student")
    private val auth = FirebaseAuth.getInstance()
    var userrr = auth.currentUser
    var isWaiting=MutableLiveData<Boolean>()
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
        isWaiting.value=true
        var userListener: ValueEventListener = object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                var counterID=0
                var counterEmail=0
                p0.children.forEach {
                    val st = it.getValue(Student::class.java)
                    if(st!=null) {
                        Log.d("MakhmudjonSUmmer",st.name)
                        if (Objects.equals(id,st.id)) {
                            counterID++
                            Log.d("MakhmudjonSUmmer","Id equal ${st.id}")
                            if(Objects.equals(email,st.email)) {
                                    sendEmailVerificationMessage(st.email,st)
                                Log.d("MakhmudjonSUmmer","Email equal ${st.id}")
                                counterEmail++
                                }
                        }
                    }
                }
                isId.value = counterID != 0
                if(counterEmail!=0) {
                    isEmail.value = true
                }else{
                    isWaiting.value=false
                    isEmail.value = false
                }

            }
        }
        database.addListenerForSingleValueEvent(userListener)
    }

    //endregion


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
    fun sendEmailVerificationMessage(email:String,student:Student?){
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
    fun signInWithEmail(email:String,student:Student?){
        auth.signInWithEmailAndPassword(email,"1234567").addOnCompleteListener{
            if(userrr!=null) {
               if (userrr!!.isEmailVerified) {
                   if(student!=null)
                       userDao.insert(student)

                       isVerified.value = true
                }
            }
        }
    }


    /**
     * To check every 2 seconds whether verified
     */
    fun loop(email:String,student:Student?){
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
        timer.schedule(doAsynchronousTask, 0, 1000)
    }
    //endregion

    //endregion

    init {
        isWaiting.value=false
    }
}