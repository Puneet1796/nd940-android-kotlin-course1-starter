package com.udacity.shoestore.screens.add

import android.view.View
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
        ) && !checkIfValid(shoe.images)
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

    private fun checkIfValid(list: List<String>): Boolean {
        return list.isNotEmpty() && list.filter { it.isNotEmpty() }.count() > 1
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

    private val _eventRemoveImageLayout = MutableLiveData<View?>()
    val eventRemoveImageLayout: LiveData<View?>
        get() = _eventRemoveImageLayout

    fun onRemoveImageLayout(view: View) {
        _eventRemoveImageLayout.value = view
    }

    fun removeImageAtIndex(index: Int) {
        shoe.images.removeAt(index)
    }

    fun onRemoveImageLayoutComplete() {
        _eventRemoveImageLayout.value = null
    }
}