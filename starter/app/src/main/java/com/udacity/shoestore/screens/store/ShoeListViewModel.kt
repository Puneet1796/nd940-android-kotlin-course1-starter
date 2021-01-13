package com.udacity.shoestore.screens.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    private val _eventAddShoeButtonClick = MutableLiveData<Boolean>()
    val eventAddShoeButtonClick: LiveData<Boolean>
        get() = _eventAddShoeButtonClick

    fun onAddButtonClicked() {
        _eventAddShoeButtonClick.value = true
    }

    fun onAddShoeEventComplete() {
        _eventAddShoeButtonClick.value = false
    }

    private val _eventListItemClick = MutableLiveData<Shoe>()
    val eventListItemClick: LiveData<Shoe>
        get() = _eventListItemClick

    fun onListItemClick(shoe: Shoe) {
        _eventListItemClick.value = shoe
    }

    fun onListItemClickComplete() {
        _eventAddShoeButtonClick.value = null
    }
}

