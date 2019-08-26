package com.example.daycounter.eventdetails

import android.app.Application
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.lifecycle.*
import com.example.daycounter.database.Event
import com.example.daycounter.database.EventDatabase
import com.example.daycounter.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate

class EventDetailsViewModel(val eventId: Long, application: Application) :
    AndroidViewModel(application) {

    private val repository: EventRepository

    private val _event = MediatorLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    private val _dateClicked = MutableLiveData<Boolean>()
    val dateClicked: LiveData<Boolean>
        get() = _dateClicked

    init {
        val eventDao = EventDatabase.getInstance(application).eventDatabaseDao
        repository = EventRepository(eventDao)

        // NOTE: When creating new event, there is a risk that when the execution reaches this point, the event is not
        // in the room yet. Fix this
        Timber.d("getting event with id ${eventId}")
        _event.addSource(repository.getEventById(eventId), _event::setValue)
//        _event = repository.getEventById(eventId)
    }

    fun updateChanges() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("update event!")
            // TODO: This may not success if the job is canceled too early
            repository.update(event.value!!)
            Timber.d("UPDATED!")
        }
    }
//
//    fun onTitleChanged(title: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//
//        }
//    }

    fun onDateClicked() {
        Timber.d("onDateClicked")

        _dateClicked.value = true
    }

    fun onDatePicked() {
        _dateClicked.value = false
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        _event.value?.let {
            it.date = LocalDate.of(year, month+1, day)
        }
    }
}
