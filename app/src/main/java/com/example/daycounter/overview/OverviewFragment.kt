package com.example.daycounter.overview


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daycounter.R
import com.example.daycounter.databinding.FragmentOverviewBinding
import timber.log.Timber

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        val binding = FragmentOverviewBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.navigateToEventDetails.observe(this, Observer { eventId ->
            eventId?.let {
                findNavController().navigate(OverviewFragmentDirections.actionShowEventDetails(eventId))
                viewModel.onNavigatedToEventDetails()
            }
        })

        val adapter = EventListAdapter(EventClickListener {
            viewModel.onEventClicked(it)
        })

        binding.eventList.adapter = adapter


        viewModel.events.observe(this, Observer { events ->
            events?.let { adapter.submitList(it) }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
