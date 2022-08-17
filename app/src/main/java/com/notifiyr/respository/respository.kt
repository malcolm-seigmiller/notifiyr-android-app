package com.notifiyr.respository

import androidx.lifecycle.LiveData
import com.google.gson.JsonArray
import com.notifiyr.database.Dao
import com.notifiyr.models.cccode

class respository(private val dao: Dao) {

    val readAllData: LiveData<List<cccode>> = dao.readAllData()

    val readSomeData: LiveData<String> = dao.readConcatenatedCode()


    suspend fun addUser(cccode: cccode) {
        dao.addcccode(cccode)
    }

    suspend fun updateUser(cccode: cccode) {
        dao.updateUser(cccode)
    }

    suspend fun deleteCccode(cccode: cccode) {
        dao.deleteCccode(cccode)
    }

    suspend fun deleteAllCccodes() {
        dao.deleteAllCccodes()
    }
}
