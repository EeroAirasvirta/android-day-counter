package com.example.daycounter.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daycounter.R
import com.example.daycounter.database.Event

class EventListAdapter : RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private var events = emptyList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTextView: TextView = itemView.findViewById(R.id.event_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        //holder.eventTextView.text = current.eventId.toString()
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }
}

class EventListener(val clickListener: (eventId: Long) -> Unit) {
    fun onClick(event: Event) = clickListener(event.eventId)
}
