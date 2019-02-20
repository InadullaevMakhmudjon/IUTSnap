package com.example.iutsummer.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.iutsummer.data.db.entity.Student
import com.example.iutsummer.utils.BasicDao

@Dao
interface UserDao: BasicDao<Student>
{
    @Query("SELECT * FROM Students")
    fun getUser():LiveData<Student>

    @Query("DELETE FROM Students")
    fun drop()
}