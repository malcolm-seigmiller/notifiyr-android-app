package com.notifiyr.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.notifiyr.database.database
import com.notifiyr.models.cccode
import com.notifiyr.respository.respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<cccode>>
    private var repository: respository

    init {
        val userDao = database.getDatabase(application).dao()
        repository = respository(userDao)
        readAllData = repository.readAllData
    }

    val readSomeData: LiveData<String>
    init {
        val userDao = database.getDatabase(application).dao()
        repository = respository(userDao)
        readSomeData = repository.readSomeData
    }

    fun addUser(cccode: cccode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(cccode)
        }
    }

    fun updateCccode(cccode: cccode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(cccode)
        }
    }

    fun deleteCccode(cccode: cccode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCccode(cccode)
        }
    }

    fun deleteAllCccodes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCccodes()
        }
    }
}