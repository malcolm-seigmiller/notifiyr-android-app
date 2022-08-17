package com.notifiyr.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mlist(
        val cccode: String,
        val header: String,
        val body: String
): Parcelable