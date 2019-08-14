package com.example.daycounter.repository

import androidx.annotation.WorkerThread
import com.example.daycounter.database.Event
import com.example.daycounter.database.EventDatabaseDao

class EventRepository(private val eventDao: EventDatabaseDao) {
    val allEvents = eventDao.getAllEvents()

    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }
}
