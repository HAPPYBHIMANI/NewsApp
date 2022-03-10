//package com.app.newsapp.utils
//
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.app.newsapp.response.ArticlesData
//import java.util.concurrent.Executors
//
//@Database(entities = [AppDatabase::class], version = 1)
//abstract class ArticleDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao?
//
//    companion object {
//        @Volatile
//        var INSTANCE: AppDatabase? = null
//        const val NUMBER_OF_THREADS = 1
//        val databaseWriteExecutor = Executors.newFixedThreadPool(
//            NUMBER_OF_THREADS
//        )
//
//        fun getDatabase(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class.java) {
//                    if (INSTANCE == null) {
//                        INSTANCE = Room.databaseBuilder(
//                            context.applicationContext,
//                            AppDatabase::class.java, "user_database"
//                        )
//                            .build()
//                    }
//                }
//            }
//            return INSTANCE
//        }
//    }
//}