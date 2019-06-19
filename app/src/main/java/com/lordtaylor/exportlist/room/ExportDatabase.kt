package com.lordtaylor.exportlist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lordtaylor.exportlist.model.ExportItem

@Database(entities = arrayOf(ExportItem::class),version = 1,exportSchema = false)
abstract class ExportDatabase :RoomDatabase(){

    abstract fun getDao():ExportDao

    companion object {
        @Volatile
        private var INSTANCE: ExportDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: getInstance(context).also { INSTANCE = it }
        }

        fun getInstance(context: Context): ExportDatabase? {
            if (INSTANCE == null) {
                synchronized(ExportDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, ExportDatabase::class.java, "AppDatabase.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}