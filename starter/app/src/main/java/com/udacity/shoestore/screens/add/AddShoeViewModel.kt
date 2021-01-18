package com.udacity.shoestore.screens.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class AddShoeViewModel : ViewModel() {
    private val _eventClickSave = MutableLiveData<Boolean?>()
    val eventClickSave: LiveData<Boolean?>
        get() = _eventClickSave

    val shoe = Shoe()

    fun onClickSave(shoe: Shoe) {
        _eventClickSave.value = !checkIfEmpty(
            shoe.name,
            shoe.company,
            shoe.size.toString(),
            shoe.description
        )
    }

    fun onClickSaveCompleted() {
        _eventClickSave.value = null
    }

    private fun checkIfEmpty(vararg values: String): Boolean {
        for (value in values) {
            if (value.isEmpty())
                return true
        }
        return false
    }

    private val _eventAddImageLayout = MutableLiveData(0)
    val eventAddImageLayout: LiveData<Int?>
        get() = _eventAddImageLayout

    fun onAddImageLayout() {
        _eventAddImageLayout.value = shoe.images.size
        shoe.images.add("")
    }

    fun onAddImageLayoutComplete() {
        _eventAddImageLayout.value = null
    }

    private val _eventRemoveImageLayout = MutableLiveData<Int?>()
    val eventRemoveImageLayout: LiveData<Int?>
        get() = _eventRemoveImageLayout

    fun onRemoveImageLayout(index: Int?) {
        if (index != null) {
            shoe.images.removeAt(index)
            _eventRemoveImageLayout.value = index
        }
    }

    fun onRemoveImageLayoutComplete() {
        _eventRemoveImageLayout.value = null
    }
}