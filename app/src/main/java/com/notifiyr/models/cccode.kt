package com.notifiyr.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cccode_table")
data class cccode(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cccode: String
): Parcelable