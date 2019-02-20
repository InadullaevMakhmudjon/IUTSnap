package com.example.iutsummer.utils

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

@Dao
interface BasicDao<T> {

    @Insert
    fun insert(entitiy:T)

    @Update
    fun update(entity:T)

    @Delete
    fun delete(entitiy: T)

}