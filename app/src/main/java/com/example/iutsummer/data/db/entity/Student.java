package com.example.iutsummer.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Students")
public class Student {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int index = 0;

    private String ID;
    private String name;
    private String year;
    private String department;
    private String yearAlias;
    private String email;

    public Student() {
    }

    public Student(String ID, String name, String year, String department, String yearAlias, String email) {
        this.index = index;
        this.ID = ID;
        this.name = name;
        this.year = year;
        this.department = department;
        this.yearAlias = yearAlias;
        this.email = email;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYearAlias() {
        return yearAlias;
    }

    public void setYearAlias(String yearAlias) {
        this.yearAlias = yearAlias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
