package com.example.daycounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import timber.log.Timber

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract val eventDatabaseDao: EventDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    Timber.d("Creating database instance")
                    instance = Room.databaseBuilder(context.applicationContext, EventDatabase::class.java, "event-db")
                        .fallbackToDestructiveMigration().build()
                    Timber.d("..Created database instance")
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
