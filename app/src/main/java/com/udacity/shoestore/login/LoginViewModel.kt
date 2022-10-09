package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Rod's template for shoe list ViewModel
class LoginViewModel : ViewModel() {

    private val _user_email = MutableLiveData<String>()
    val user_email : LiveData<String>
        get() = _user_email

    private val _user_password = MutableLiveData<String>()
    val user_pwd : LiveData<String>
        get() = _user_password

    val input_email = MutableLiveData<String>()

    private val _validLogin = MutableLiveData<Boolean>()
    val loginCorrect : LiveData<Boolean>
        get() = _validLogin

    init {
        _user_email.value = "rod@email.com" // private field simulating database value
        _user_password.value = "Password" // private field simulating database value
    }

    fun onLoginPressed(){
        _validLogin.value = true
    }

}