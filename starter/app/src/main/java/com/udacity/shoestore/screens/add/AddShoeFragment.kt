package com.udacity.shoestore.screens.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] class
 */
class AddShoeFragment : Fragment() {
    private lateinit var binding: AddShoeFragmentBinding
    private lateinit var viewModel: AddShoeViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_shoe_fragment, container, false)

        binding.submitShoeBtn.setOnClickListener {
            mainViewModel.onShoeSubmit(
                Shoe(
                    name = "Nike AirX",
                    size = 8.0,
                    company = "Nike",
                    description = "Nike AirX is for the players who wants to go beyond the Sky.",
                    images = listOf()
                )
            )
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddShoeViewModel::class.java]
    }
}