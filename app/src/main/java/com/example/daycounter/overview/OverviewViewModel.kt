package com.example.daycounter.overview

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.daycounter.database.Event
import com.example.daycounter.database.EventDatabase
import com.example.daycounter.repository.EventRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import timber.log.Timber

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository
    val events: LiveData<List<Event>>

    init {
        val eventDao = EventDatabase.getInstance(application).eventDatabaseDao
        repository = EventRepository(eventDao)

        events = repository.allEvents
    }

    private val _navigateToEventDetails = MutableLiveData<Long>()
    val navigateToEventDetails: LiveData<Long>
        get() = _navigateToEventDetails

    fun onNewEventClicked() {
        Timber.d("onNewEventClicked")

        viewModelScope.launch(Dispatchers.IO) {
            val newEvent = Event()
            Timber.d("Created new event (id: ${newEvent.eventId})")
            val newId = repository.insert(newEvent)
            Timber.d("actual id: ${newId}")

            // Todo: Disable FAB after clicking it
            _navigateToEventDetails.postValue(newId)
        }
    }

    fun onEventClicked(event: Event) {
        Timber.d("onEventClicked(eventId: ${event.eventId})")
        _navigateToEventDetails.value = event.eventId
    }

    fun onNavigatedToEventDetails() {
        Timber.d("onNavigatedToEventDetails")
        _navigateToEventDetails.value = null
    }

    fun insert(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }
}
