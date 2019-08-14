package com.example.daycounter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event_table")
data class Event(

    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0L,

    @ColumnInfo(name = "event_date")
    val date: Calendar = Calendar.getInstance(),

    var title: String = "",

    var description: String = ""
)
