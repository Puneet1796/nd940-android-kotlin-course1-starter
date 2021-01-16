package com.udacity.shoestore.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding

class ShoeDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ShoeDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_details_fragment, container, false)

        val shoe = ShoeDetailsFragmentArgs.fromBundle(requireArguments()).shoe
        binding.shoe = shoe

        return binding.root
    }
}