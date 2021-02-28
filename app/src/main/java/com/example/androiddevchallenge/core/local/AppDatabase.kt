package com.example.androiddevchallenge.core.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.androiddevchallenge.core.local.repo.PuppyDao
import com.example.androiddevchallenge.core.model.PuppyEntity

@Database(entities = [PuppyEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "puppy.db"
                    ).createFromAsset("databases/puppys.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract val puppyDao: PuppyDao


}


