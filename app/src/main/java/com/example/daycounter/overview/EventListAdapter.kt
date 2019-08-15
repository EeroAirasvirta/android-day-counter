package com.example.daycounter.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daycounter.database.Event
import com.example.daycounter.databinding.ListItemEventBinding

class EventListAdapter(private val onClickListener: EventClickListener) :
    ListAdapter<Event, EventListAdapter.ViewHolder>(EventDiffCallback()) {

    //    private var events = emptyList<Event>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onClickListener, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: EventClickListener, event: Event) {
            binding.event = event
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemEventBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class EventClickListener(val clickListener: (event: Event) -> Unit) {
    fun onClick(event: Event) = clickListener(event)
}


private class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.eventId == newItem.eventId
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}
