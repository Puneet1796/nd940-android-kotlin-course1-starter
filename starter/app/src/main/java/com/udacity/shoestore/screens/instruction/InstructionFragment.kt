package com.udacity.shoestore.screens.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionFragmentBinding

class InstructionFragment : Fragment() {
    private lateinit var viewModel: InstructionViewModel
    private lateinit var binding: InstructionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.instruction_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[InstructionViewModel::class.java]

        viewModel.eventClickNext.observe(viewLifecycleOwner) { hasPerformed ->
            if (hasPerformed) {
                findNavController()
                    .navigate(R.id.action_instructionFragment_to_storeFragment)
                viewModel.onClickNextCompleted()
            }
        }

        binding.viewModel = viewModel
    }
}