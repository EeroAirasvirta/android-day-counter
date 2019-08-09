package com.example.daycounter

import android.app.Application
import timber.log.Timber

class DayCounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
