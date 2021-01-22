package com.udacity.shoestore.screens.store

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.databinding.ShoeListItemLayoutBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] subclass.
 */
class ShoeListFragment : Fragment() {
    private lateinit var binding: ShoeListFragmentBinding
    private lateinit var viewModel: ShoeListViewModel

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.shoe_list_fragment, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            mainViewModel.onLogoutSuccess()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShoeListViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        populateData()
    }

    private fun populateData() {
        mainViewModel.listOfShoes.observe(viewLifecycleOwner) { listOfShoes ->
            if (listOfShoes.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.emptyView.visibility = View.GONE
            }
            binding.listContainer.removeAllViews()
            for (shoe in listOfShoes)
                binding.listContainer.addView(
                    createListItem(shoe),
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
        }
        viewModel.eventAddShoeButtonClick.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked == true) {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment()
                findNavController().navigate(action)
                viewModel.onAddShoeEventComplete()
            }
        }
        viewModel.eventListItemClick.observe(viewLifecycleOwner) { shoe: Shoe? ->
            if (shoe != null) {
                val action =
                    ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoe)
                findNavController().navigate(action)
                viewModel.onListItemClickComplete()
            }
        }
    }

    private fun createListItem(shoe: Shoe): View {
        val listItemBinding: ShoeListItemLayoutBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.shoe_list_item_layout,
                binding.listContainer,
                false
            )

        listItemBinding.shoeCoverImage.load(shoe.images[0]) {
            crossfade(true)
            placeholder(R.drawable.ic_trainers)
            transformations(RoundedCornersTransformation(8f))
        }

        listItemBinding.shoe = shoe
        listItemBinding.viewModel = viewModel
        return listItemBinding.root
    }
}