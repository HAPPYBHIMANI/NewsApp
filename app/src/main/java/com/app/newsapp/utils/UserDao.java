package com.app.newsapp.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.newsapp.response.ArticlesData;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid = :id")
    List<User> loadAllById(int id);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    /////Articles
    @Query("SELECT * FROM articlesdata")
    List<ArticlesData> getArticles();


    @Insert
    void insertArticles(ArticlesData... articlesData);

    @Delete
    void delete(ArticlesData articlesData);

    @Query("DELETE FROM ArticlesData")
    void deleteArticles();
}