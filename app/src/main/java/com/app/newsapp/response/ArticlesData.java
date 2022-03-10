package com.app.newsapp.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArticlesData {
    public ArticlesData() {
    }

    public ArticlesData(String author, String name) {
        this.author = author;
        this.description = description;
    }

    @PrimaryKey(autoGenerate = true)
    public int title;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "description")
    public String description;

//
//        return sdf.format(new Date(timestamp));
//    public String getDateString() {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
//    }
//
    public String getDataList() {
        return "Description: " + description + " Author: " + author;
    }
}
