package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    private val _eventClickNext = MutableLiveData<Boolean>()
    val eventClickNext: LiveData<Boolean>
        get() = _eventClickNext

    fun onClickNext() {
        _eventClickNext.value = true
    }

    fun onClickNextCompleted() {
        _eventClickNext.value = false
    }
}