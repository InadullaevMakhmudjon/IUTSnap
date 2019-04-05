package com.example.iutsummer.ui.user.lostfound

import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.data.db.entity.News
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LostFoundRepository(val app: App) {

    private val database = app.appMode.database.getLostItemDao
    private val firebaseDb = FirebaseDatabase.getInstance().reference.child("LostFound")

    var allItems = database.allItems()

    fun getAllItems(){
        val listiner = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                database.deleteItem()
                p0.children.forEach {
                    val item = it.getValue(LItem::class.java)
                    if(item!=null){
                        database.insert(item)
                    }
                }
            }
        }
        firebaseDb.addValueEventListener(listiner)
    }
}