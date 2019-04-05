package com.example.iutsummer.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.iutsummer.data.db.entity.News
import com.example.iutsummer.utils.BasicDao

@Dao
interface NewsDao: BasicDao<News> {

    @Query("SELECT * FROM News")
    fun allNews():LiveData<List<News>>

    @Query("DELETE FROM News")
    fun dropAllNews()
}