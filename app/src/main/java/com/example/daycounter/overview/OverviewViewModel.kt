package com.example.daycounter.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daycounter.database.EventDatabaseDao
import timber.log.Timber

class OverviewViewModel(val database: EventDatabaseDao,
                        application: Application) : ViewModel() {

    private val _navigateToEventDetails = MutableLiveData<Boolean>()
    val navigateToEventDetails: LiveData<Boolean>
        get() = _navigateToEventDetails

    fun onNewEventClicked() {
        Timber.d("onNewEventClicked")
        _navigateToEventDetails.value = true
    }

    fun onNavigatedToEventDetails() {
        Timber.d("onNavigatedToEventDetails")
        _navigateToEventDetails.value = false
    }


}
