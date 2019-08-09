package com.example.daycounter.eventdetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daycounter.databinding.FragmentEventDetailsBinding

class EventDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val binding = FragmentEventDetailsBinding.inflate(inflater, R.layout.fragment_event_details, container, false)
        val binding = FragmentEventDetailsBinding.inflate(inflater)

        return binding.root
    }


}
