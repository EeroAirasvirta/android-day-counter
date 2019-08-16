package com.example.daycounter.overview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.daycounter.database.Event
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat

@BindingAdapter("eventDate")
fun TextView.setEventDate(item: Event?) {
    val dateFormat = SimpleDateFormat("d.M.yyyy")
    item?.let {
        text = dateFormat.format(item.date.time)
    }
}


// TODO: Only for debugging purposes. Remove later
@BindingAdapter("eventId")
fun TextView.setEventId(item: Event?) {
    item?.let {
        text = it.eventId.toString()
    }
}
