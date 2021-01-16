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
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeFragmentBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] class
 */
class AddShoeFragment : Fragment() {
    private lateinit var binding: AddShoeFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var viewModel: AddShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_shoe_fragment, container, false)
        return binding.root
    }

    private fun checkIfEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (value.isEmpty())
                return true
        }
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddShoeViewModel::class.java]

        viewModel.eventClickNext.observe(viewLifecycleOwner) { hasPerformed ->
            if (hasPerformed) {
                val shoeName = binding.valueShoeName.text.toString()
                val shoeCompany = binding.valueShoeCompany.text.toString()
                val shoeSize = binding.valueShoeSize.text.toString()
                val shoeDescription = binding.valueShoeDescription.text.toString()

                if (checkIfEmpty(shoeName, shoeCompany, shoeSize, shoeDescription)) {
                    Snackbar.make(
                        requireView(),
                        "Please fill all the fields.",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAnchorView(binding.submitShoeBtn)
                        .show()
                } else {
                    mainViewModel.onShoeSubmit(
                        Shoe(
                            shoeName,
                            if (shoeSize.isEmpty()) 0.0 else shoeSize.toDouble(),
                            shoeCompany,
                            shoeDescription,
                            listOf()
                        )
                    )
                    findNavController().navigateUp()
                }
                viewModel.onClickNextCompleted()
            }
        }

        binding.viewModel = viewModel
    }
}