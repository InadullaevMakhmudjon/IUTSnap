package com.example.iutsummer.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.data.db.entity.Student
import com.example.iutsummer.databinding.ActivityMainBinding
import com.example.iutsummer.utils.ObservableViewModel
import java.util.*

class MainaActivityViewModel(appContext:Application,bindings: ActivityMainBinding): ObservableViewModel(appContext) {

    //region TwoWay Bindings
    /**
     * Id EditText's text
     */
    @Bindable
    var userIdtext = MutableLiveData<String>()

    /**
     * Email EditText's text
     */
    @Bindable
    var userEmailtext = MutableLiveData<String>()

    //endregion

    //region private properties
    /**
     * Application Context instance
     */
    private val applicationContext = appContext

    /**
     * Repository instance
     */
    private val repository = MainActivityRepository(applicationContext as App)

    /**
     * viewBindings to access other view
     */
    private val binding = bindings

    /**
     * Shake animation instance
     */
    private val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.shake)

    //endregion

    //region public properties and bindings
    /**
     * Triggers when user exists
     */
    var iutStudents:LiveData<Student>

    /**
     * Checks ID
     */
    var isIDCorrect:LiveData<Boolean>

    /**
     * Checks Email
     */
    var isEmailCorrect:LiveData<Boolean>

    /**
     * Updates UI when email verification send
     */
    var isSendEmail:LiveData<Boolean>

    //endregion

    //region public methods
    /**
     * SignIn Click Event
     *      1.Checks whether Email is wrong or empty and Id is empty
     *      2.Animate UI if one of them false
     *      3.if all ok then calls getUser() method in Repository
     */
    fun signIn(v:View) {
        // Validation handling
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Objects.equals(userIdtext.value,"")) {
                binding.userId.background = applicationContext.getDrawable(R.drawable.ed_bg_red)
                binding.userId.startAnimation(animation)
            }
            if(Objects.equals(userEmailtext.value,"")){
                binding.userEmail.background = applicationContext.getDrawable(R.drawable.ed_bgo_red)
                binding.userEmail.startAnimation(animation)
            }
            else {
                repository.getUser("U"+userIdtext.value,binding.userEmail.text.toString())
            }
        }
    }


    /**
     * Deletes student from database
     */
    fun dropStudent() = repository.dropStudent()

    //endregion

    //region initial object
    /**
     * Initial step when class created
     */
    init {
        iutStudents = repository.iutStudent
        isIDCorrect=repository.isId
        isEmailCorrect=repository.isEmail
        isSendEmail=repository.isSendEmail
    }

    //endregion

}