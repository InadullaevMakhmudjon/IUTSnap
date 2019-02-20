package com.example.iutsummer.data.db.entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Students")
class Student{

   @NonNull
   @PrimaryKey(autoGenerate = true)
   var index:Int = 0

   @SerializedName("ID")
   @ColumnInfo(name = "id")
   lateinit var ID:String

   @SerializedName("Name")
   @ColumnInfo(name = "name")
   lateinit var Name:String


}