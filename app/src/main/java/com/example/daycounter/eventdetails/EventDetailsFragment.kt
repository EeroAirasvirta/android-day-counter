package com.example.daycounter.eventdetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.daycounter.databinding.FragmentEventDetailsBinding
import timber.log.Timber

class EventDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        val binding = FragmentEventDetailsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val args = EventDetailsFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = EventDetailsViewModelFactory(args.eventId, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(EventDetailsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }


}
