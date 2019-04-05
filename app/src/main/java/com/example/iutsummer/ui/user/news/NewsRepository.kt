package com.example.iutsummer.ui.user.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.News
import com.example.iutsummer.ui.user.news.adapters.NewsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewsRepository(private val app:App) {

    private val firebaseDb = FirebaseDatabase.getInstance().reference.child("News")
    private val database = app.appMode.database.getNewsDao

    val allNews= database.allNews()

    fun initializeNews(){
        val listiner = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val news = it.getValue(News::class.java)
                    if(news!=null){
                       database.insert(news)
                    }
                }
            }
        }
        firebaseDb.addValueEventListener(listiner)
    }

    fun deleteAllNews() = database.dropAllNews()
}