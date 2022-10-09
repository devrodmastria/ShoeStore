package com.udacity.shoestore.shoe_gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private val sampleShoeA = Shoe("Sandal", 11.0, "Havaianas","always cool", mutableListOf())
    private val sampleShoeB = Shoe("Boots", 7.0, "Muddy&Co","always tough", mutableListOf())

    private val shoeList = mutableListOf<Shoe>().apply {
        add(sampleShoeA)
        add(sampleShoeB)
    }
    var shoeLiveDataList : MutableLiveData<List<Shoe>> = MutableLiveData<List<Shoe>>().apply {
        postValue(shoeList)
    }

    // skip login screen for returning users
    private val _loginCompleted = MutableLiveData<Boolean>()

    // encapsulated login status
    val loginCompleted: LiveData<Boolean>
        get() = _loginCompleted

    init {
        _loginCompleted.value = false
    }

    fun addShoe(shoe: Shoe){

        val newShoeItem = Shoe(shoe.name, shoe.size, shoe.company, shoe.description, mutableListOf())
        shoeList.add(newShoeItem)
        shoeLiveDataList.postValue(shoeList)

        Timber.wtf("-->> Shoe Item ADDED")
    }

    fun setLoginComplete(){
        _loginCompleted.value = true
    }

    fun resetLogin(){
        _loginCompleted.value = false
    }

}