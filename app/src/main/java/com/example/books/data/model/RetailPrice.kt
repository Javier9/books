package com.example.books.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetailPrice(
    val amountInMicros: Int,
    val currencyCode: String
):Parcelable