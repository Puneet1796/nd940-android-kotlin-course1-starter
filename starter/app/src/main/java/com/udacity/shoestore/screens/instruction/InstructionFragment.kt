package com.udacity.shoestore.screens.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionFragmentBinding

class InstructionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: InstructionFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.instruction_fragment, container, false)
        binding.nextBtn.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_instructionFragment_to_storeFragment)
        }
        return binding.root
    }
}