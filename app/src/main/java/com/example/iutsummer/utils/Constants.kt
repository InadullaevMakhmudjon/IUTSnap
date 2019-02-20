package com.example.iutsummer.utils

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.example.iutsummer.R
import com.flyco.animation.BounceEnter.BounceRightEnter
import com.flyco.animation.SlideExit.SlideLeftExit
import com.flyco.dialog.widget.popup.BubblePopup
import kotlinx.android.synthetic.main.popup_error.view.*

fun View.showMessage(message:String){
    Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
}

fun View.snackBar(message: String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}

fun View.showErrorBuble(message:String,anchor:View){
    val inflate = View.inflate(this.context, R.layout.popup_error, null)
    inflate.mainContent.text = message
    val bubblePopup = BubblePopup(this.context, inflate)
    bubblePopup.anchorView(anchor)
        .showAnim(null)
        .dismissAnim(SlideLeftExit())
        .autoDismiss(true)
        .show()
}