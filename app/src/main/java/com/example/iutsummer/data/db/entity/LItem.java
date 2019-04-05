package com.example.iutsummer.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


@Entity(tableName = "LItem")
public class LItem {

    @PrimaryKey(autoGenerate = true)
    private int index = 0;

    private String photo;
    private String description;
    private String date;
    private String category;

    public LItem() {
    }

    public LItem(String photo, String description, String date, String category) {
        this.photo = photo;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("photo",photo);
        result.put("description",description);
        result.put("date",date);
        result.put("category",category);
        return result;
    }
}
