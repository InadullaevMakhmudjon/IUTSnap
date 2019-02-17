package com.example.iutsummer.utils

import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

fun View.showMessage(message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}

fun View.snackBar(message: String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}