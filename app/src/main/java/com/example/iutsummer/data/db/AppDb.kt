package com.example.iutsummer.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.iutsummer.data.db.dao.LostItem
import com.example.iutsummer.data.db.dao.NewsDao
import com.example.iutsummer.data.db.dao.UserDao
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.data.db.entity.News
import com.example.iutsummer.data.db.entity.Student

@Database(entities = [Student::class,News::class, LItem::class],version = 18,exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract val getUserDao:UserDao
    abstract val getNewsDao:NewsDao
    abstract val getLostItemDao:LostItem
}