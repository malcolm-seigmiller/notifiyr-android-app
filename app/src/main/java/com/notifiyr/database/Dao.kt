package com.notifiyr.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.google.android.gms.common.util.Strings
import com.google.gson.JsonArray
import com.notifiyr.models.cccode

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addcccode(cccode: cccode)

    @Query("SELECT * FROM cccode_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<cccode>>

    @Query("SELECT GROUP_CONCAT(cccode) FROM cccode_table")
    fun readConcatenatedCode(): LiveData<String>

    @Update
    suspend fun updateUser(cccode: cccode)

    @Delete
    suspend fun deleteCccode(cccode: cccode)

    @Query("DELETE FROM cccode_table")
    suspend fun deleteAllCccodes()
}