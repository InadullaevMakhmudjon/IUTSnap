package com.example.iutsummer.ui.user.lostfound.dialog

import android.app.DatePickerDialog
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.databinding.Bindable
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.ui.user.lostfound.LostFoundFragment
import com.example.iutsummer.utils.ObservableViewModel
import com.example.iutsummer.utils.SelectedDate
import java.io.File
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class DialogLFViewModel(val app:App, val parent: LostFoundFragment,val imageView:ImageView):ObservableViewModel(app) {

    val REQUEST_IMAGE_CAPTURE = 1

    val date = Calendar.getInstance().time

    val repository = DialogLFRepository(app)

    var response = repository.response

    @Bindable
    var dataText = MutableLiveData<String>()

    @Bindable
    var category = MutableLiveData<String>()

    @Bindable
    var desctiption = MutableLiveData<String>()

    /**
     * Creating file to get a picture
     */
    var tempFile = File.createTempFile("camera", ".png", app.externalCacheDir)
    var mPath = tempFile.absolutePath
    var uri = FileProvider.getUriForFile(app.applicationContext,"com.example.asd.fileprovider",tempFile)

    /**
     * Category
     */
    fun dataClick(v:View){
        val popupMenu = PopupMenu(app,v)
        popupMenu.menuInflater.inflate(R.menu.lostfound_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                category.value = item!!.title.toString()
                return true
            }
        })
        popupMenu.show()
    }

    /**
     *Gets date
     */
    fun dataPick(v:View){
       val datedialog = DatePickerDialog(v.context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            dataText.value = "$dayOfMonth/${getMonth(month)}/$year"
        }, SelectedDate.year, SelectedDate.month, SelectedDate.Day)
        datedialog.show()
    }

    /**
     * Sets image on imageView
     */
    fun setImage(imageView:ImageView){
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        val bitmap = BitmapFactory.decodeFile(mPath, options)
        imageView.setPadding(0,0,0,0)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setImageBitmap(bitmap)
    }

    /**
     * Gets Picture from camera
     */
    fun capture(v:View){
       val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
           parent.startActivityForResult(intent,REQUEST_IMAGE_CAPTURE)
    }

    /**
     * Converts from integer to equivalent month name
     */
    fun getMonth(month: Int): String = DateFormatSymbols().months[month]

    /**
     * Submit button action
     */
    fun submit(v:View){
        val item = LItem(
            "https://makhmudjon",
            desctiption.value,
            dataText.value,
            category.value
        )
        repository.insertFirebase(item,imageView)
    }

    init {
        val spf= SimpleDateFormat("dd/MMM/yyyy")
        dataText.value = spf.format(date)
        category.value = "Category"
    }
}