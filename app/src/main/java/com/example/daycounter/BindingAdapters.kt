package com.example.daycounter

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.daycounter.database.Event
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import kotlin.math.absoluteValue

@BindingAdapter("eventAgo")
fun TextView.setEventDate(item: Event?) {
    item?.let {
        val period = Duration.between(it.date.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays()
        //val period = Period.between(it.date, LocalDate.now()).

        val dayString = when (period.absoluteValue) {
            1L -> "${period.absoluteValue} day"
            else -> "${period.absoluteValue} days"
        }

        val futureOrPastEventString = when {
            period < 0 -> "left"
            else -> "ago"
        }

        text = "$dayString $futureOrPastEventString"
    }

}

@BindingAdapter("eventDate")
fun EditText.setDate(date: LocalDate?) {
    date?.let {
        val result = "${it.dayOfMonth}.${it.monthValue}.${it.year}"
//        val result = "11.2.2019"
        setText(result)
    }
}
