package com.example.poejwct_1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class DataItem(val type: String, val marks: String) : Parcelable
