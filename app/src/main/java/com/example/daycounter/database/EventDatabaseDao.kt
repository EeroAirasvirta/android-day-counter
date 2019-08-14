package com.example.daycounter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDatabaseDao {

    @Insert
    suspend fun insert(event: Event)

    @Query("SELECT * FROM event_table ORDER BY eventId DESC")
    fun getAllEvents(): LiveData<List<Event>>

/*    @Query("SELECT * from event_table WHERE eventId = :key")
    fun get(key: Long): Event?*/
}
