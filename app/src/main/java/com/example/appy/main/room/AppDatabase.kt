package com.example.appy.main.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appy.main.model.Text

@Database(entities = [Text::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun textDao(): TextDao

    companion object {
        private const val DB_NAME = "little"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets [AppDatabase] instance.
         *
         * @param context [Context]
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}