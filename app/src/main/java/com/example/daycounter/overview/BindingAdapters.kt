package com.example.daycounter.overview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.daycounter.database.Event
import java.text.SimpleDateFormat

@BindingAdapter("eventDate")
fun TextView.setEventDate(item: Event?) {
    val dateFormat = SimpleDateFormat("d.M.yyyy")
    item?.let {
        text = dateFormat.format(item.date.time)
    }
}
