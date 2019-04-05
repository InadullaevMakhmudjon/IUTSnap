package com.example.iutsummer.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.utils.BasicDao

@Dao
interface LostItem: BasicDao<LItem> {

    @Query("SELECT * FROM LItem")
    fun allItems(): LiveData<List<LItem>>

    @Query("DELETE FROM LItem")
    fun deleteItem()

}