package com.example.daycounter.database

import android.content.Context
import androidx.room.*
import timber.log.Timber

@Database(entities = [Event::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase() {

    abstract val eventDatabaseDao: EventDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {

                Timber.d("Creating database instance")
                val instance = Room.databaseBuilder(context.applicationContext, EventDatabase::class.java, "event-db")
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
