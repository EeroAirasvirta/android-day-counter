package com.example.daycounter.eventdetails


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daycounter.databinding.FragmentEventDetailsBinding
import timber.log.Timber
import java.time.LocalDate

class EventDetailsFragment : Fragment() {

    private lateinit var viewModel: EventDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        val binding = FragmentEventDetailsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val args = EventDetailsFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = EventDetailsViewModelFactory(args.eventId, application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(EventDetailsViewModel::class.java)

        viewModel.dateClicked.observe(this, Observer { clicked ->
            if (clicked) {
                val date = viewModel.event.value?.date!!
                val dpd = DatePickerDialog(
                    context!!,
                    DatePickerDialog.OnDateSetListener { _, year, month, day ->
                        viewModel.onDateSelected(year, month, day)
                    }, date.year, date.monthValue - 1, date.dayOfMonth
                )
                dpd.show()
                viewModel.onDatePicked()
            }
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateChanges()

    }
}
