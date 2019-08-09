package com.example.daycounter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDatabaseDao {

    @Insert
    fun insert(event: Event)

/*    @Query("SELECT * from event_table WHERE eventId = :key")
    fun get(key: Long): Event?*/
}
