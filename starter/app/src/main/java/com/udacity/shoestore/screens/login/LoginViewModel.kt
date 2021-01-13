package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val _eventAuth = MutableLiveData<Boolean>()
    val eventAuth: LiveData<Boolean>
        get() = _eventAuth

    fun onAuth(email: String, password: String) {
        Timber.i("User: $email\tPassword: $password")
        when {
            email.isEmpty() ->
                _eventAuth.value = false
            password.isEmpty() ->
                _eventAuth.value = false
            else -> {
                _eventAuth.value = true
            }
        }
    }

    fun onRegister() {
        _eventAuth.value = true
    }
}