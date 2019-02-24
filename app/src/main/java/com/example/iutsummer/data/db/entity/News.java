package com.example.iutsummer.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "News")
public class News {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int index = 0;

    private String BodyText;
    private String Date;
    private String Image;
    private String Month;
    private String TitleText;

    public News() {
    }

    public News(String bodyText, String date, String image, String month, String titleText) {
        BodyText = bodyText;
        Date = date;
        Image = image;
        Month = month;
        TitleText = titleText;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBodyText() {
        return BodyText;
    }

    public void setBodyText(String bodyText) {
        BodyText = bodyText;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getTitleText() {
        return TitleText;
    }

    public void setTitleText(String titleText) {
        TitleText = titleText;
    }
}
