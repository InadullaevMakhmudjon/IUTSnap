package com.example.iutsummer.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.iutsummer.data.db.dao.UserDao
import com.example.iutsummer.data.db.entity.Student

@Database(entities = [Student::class],version = 9,exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract val getUserDao:UserDao
}