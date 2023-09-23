package com.andridm.kampusapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kampus(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
