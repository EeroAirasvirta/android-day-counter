package com.example.daycounter.overview


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.daycounter.R
import com.example.daycounter.database.EventDatabase
import com.example.daycounter.databinding.FragmentOverviewBinding
import timber.log.Timber

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = EventDatabase.getInstance(application).eventDatabaseDao

        val viewModelFactory = OverviewViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OverviewViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.navigateToEventDetails.observe(this, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(OverviewFragmentDirections.actionShowEventDetails())
                viewModel.onNavigatedToEventDetails()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
