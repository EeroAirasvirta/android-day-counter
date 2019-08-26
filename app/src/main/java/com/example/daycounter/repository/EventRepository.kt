package com.example.daycounter.repository

import androidx.annotation.WorkerThread
import com.example.daycounter.database.Event
import com.example.daycounter.database.EventDatabaseDao

class EventRepository(private val eventDao: EventDatabaseDao) {
    val allEvents = eventDao.getAllEvents()

    @WorkerThread
    suspend fun insert(event: Event): Long {
        return eventDao.insert(event)
    }

    @WorkerThread
    fun getEventById(eventId: Long) = eventDao.get(eventId)

    fun update(event: Event) = eventDao.updateEvent(event)
}
