package com.example.daycounter.database

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun localDateToDatestamp(date: LocalDate): Long = date.toEpochDay()

    @TypeConverter
    fun datestampToLocalDate(value: Long): LocalDate = LocalDate.ofEpochDay(value)
}
