package com.udacity.shoestore.screens.add

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddImageListItemLayoutBinding
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddShoeViewModel::class.java]
        val shoe = Shoe()

        viewModel.eventClickSave.observe(viewLifecycleOwner) { isValid: Boolean? ->
            if (isValid != null) {
                if (isValid) {
                    mainViewModel.onShoeSubmit(shoe)
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(
                        requireView(),
                        "Please fill all the fields.",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAnchorView(binding.submitShoeBtn)
                        .show()
                }
                viewModel.onClickSaveCompleted()
            }
        }

        viewModel.eventAddImageLayout.observe(viewLifecycleOwner) { addedIndex: Int? ->
            if (addedIndex != null) {
                appendImageLayout(shoe, addedIndex)
                viewModel.onAddImageLayoutComplete()
            }
        }

        viewModel.eventRemoveImageLayout.observe(viewLifecycleOwner) { view: View? ->
            if (view != null) {
                val count = binding.addImageList.childCount
                if (count == 1) {
                    Toast.makeText(
                        requireContext(),
                        "Product must have at least one image.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@observe
                }
                val index = binding.addImageList.children.indexOf(view.parent as ConstraintLayout)
                removeImageLayout(index)
                viewModel.removeImageAtIndex(index)
                viewModel.onRemoveImageLayoutComplete()
            }
        }

        binding.viewModel = viewModel
        binding.shoe = shoe
    }

    private fun appendImageLayout(shoe: Shoe, nextIndex: Int) {
        val innerBinding: AddImageListItemLayoutBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.add_image_list_item_layout,
                binding.addImageList,
                false
            )

        if (nextIndex > 0) {
            val newLayoutParams = innerBinding.root.layoutParams as LinearLayout.LayoutParams
            newLayoutParams.topMargin = 16.dpToPx()
            innerBinding.root.layoutParams = newLayoutParams
        }

        binding.addImageList.addView(innerBinding.root, nextIndex)

        innerBinding.shoe = shoe
        innerBinding.index = nextIndex
        innerBinding.viewModel = viewModel
    }

    private fun removeImageLayout(removedIndex: Int) {
        binding.addImageList.removeViewAt(removedIndex)
    }

    private fun Int.dpToPx(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}