package com.example.kotlinpractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinpractice.model.RepositoryData
import com.example.kotlinpractice.model.TypeConverterCountryInfo

@Database(entities = [RepositoryData::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterCountryInfo::class)
abstract  class AppDatabase:RoomDatabase() {

    abstract fun getAppDao():AppDao
    companion object{
        private var DB_INSTANCE:AppDatabase? = null

        fun getAppDBInstance(context: Context):AppDatabase {
            if(DB_INSTANCE == null){
                DB_INSTANCE = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"APP_DB")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}