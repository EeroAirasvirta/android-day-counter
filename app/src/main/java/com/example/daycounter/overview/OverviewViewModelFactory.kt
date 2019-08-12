package com.example.daycounter.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daycounter.database.EventDatabaseDao

class OverviewViewModelFactory(private val database: EventDatabaseDao,
                               private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unkonwn viewmodel class")
    }
}
