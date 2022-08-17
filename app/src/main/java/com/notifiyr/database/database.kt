package com.notifiyr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.notifiyr.models.cccode

@Database(entities = [cccode::class],
        version = 1, exportSchema = false
)
abstract class database: RoomDatabase() {

    abstract fun dao():Dao
    companion object{
        @Volatile
        private var INSTANCE: database? = null
        fun getDatabase(context: Context): database{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}