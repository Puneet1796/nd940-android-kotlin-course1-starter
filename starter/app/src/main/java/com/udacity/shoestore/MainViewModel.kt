package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _listOfShoes = MutableLiveData<List<Shoe>>()
    val listOfShoes: LiveData<List<Shoe>>
        get() = _listOfShoes

    init {
        _listOfShoes.value = emptyList()
    }

    fun onShoeSubmit(shoe: Shoe) {
        if (_listOfShoes.value != null) {
            val tempList = _listOfShoes.value!!.toMutableList()
            tempList.add(shoe)
            _listOfShoes.value = tempList
        }
    }

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    fun onLoginSuccess() {
        _eventLogin.value = true
    }

    fun onLogoutSuccess() {
        _eventLogin.value = false
    }
}