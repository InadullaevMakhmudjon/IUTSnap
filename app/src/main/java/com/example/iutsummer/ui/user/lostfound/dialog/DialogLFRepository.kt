package com.example.iutsummer.ui.user.lostfound.dialog

import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.ImageView
import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.utils.showMessage
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream

class DialogLFRepository(val app:App) {
    private val firebaseDb = FirebaseDatabase.getInstance().reference.child("LostFound")
    private var storage = FirebaseStorage.getInstance().reference

    private val databse = app.appMode.database.getLostItemDao

    /**
     * Triggers parent fragment when data comes from firebase
     */
    var response = MutableLiveData<String>()

    fun insertFirebase(item: LItem,img:ImageView){
        val data = getByteArray(img)
        val spaceFile = storage.child(System.currentTimeMillis().toString())
        val uploadeTask = spaceFile.putBytes(data)

        val urlTask = uploadeTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation spaceFile.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                item.photo = downloadUri.toString()
                writeDatabase(item)
            } else {
               img.showMessage("Failed to uploade")
            }
        }
    }

    fun writeDatabase(item: LItem){
        val key = firebaseDb.push().key ?: return
        val post = item.toMap()

        val childUpdates = HashMap<String, Any>()
        childUpdates["/$key"] = post
        firebaseDb.updateChildren(childUpdates).addOnSuccessListener {
            response.value = key
        }
    }

    private fun getByteArray(image:ImageView): ByteArray {
        image.isDrawingCacheEnabled = true
        image.buildDrawingCache()
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
         bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos)
        return  baos.toByteArray()
    }
}