package com.example.retrofit_1221.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofit_1221.model.POJO.MarsTerrain

@Database(entities = [MarsTerrain::class], version = 1)
abstract class MarsDatabase : RoomDatabase() {

    abstract fun getMarsDao() : MarsDao

    companion object {
        @Volatile
        private var INSTANCE : MarsDatabase? = null

        fun getDataBase(context: Context): MarsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    MarsDatabase::class.java,
                    "marsDB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}