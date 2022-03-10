package com.app.newsapp.utils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class User2 {
    public User2() {
    }

    public User2(long timestamp, String name) {
        this.timestamp = timestamp;
        this.name = name;
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "name")
    public String name;

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");

        return sdf.format(new Date(timestamp));
    }

    public String getDebugString() {
        return "UID: " + uid + " Timestamp: " + getDateString() + " Name: " + name;
    }
}
