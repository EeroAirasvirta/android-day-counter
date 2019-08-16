package com.example.daycounter.eventdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.daycounter.database.Event
import com.example.daycounter.database.EventDatabase
import com.example.daycounter.repository.EventRepository
import timber.log.Timber

class EventDetailsViewModel(eventId: Long, application: Application): AndroidViewModel(application) {

    private val repository: EventRepository

    val event: LiveData<Event>

    init {
        val eventDao = EventDatabase.getInstance(application).eventDatabaseDao
        repository = EventRepository(eventDao)

        // NOTE: When creating new event, there is a risk that when the execution reaches this point, the event is not
        // in the room yet. Fix this
        Timber.d("getting event with id ${eventId}")
        event = repository.getEventById(eventId)
    }
}
