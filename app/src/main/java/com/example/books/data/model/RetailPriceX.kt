package com.example.books.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetailPriceX(
    val amount: Double,
    val currencyCode: String
):Parcelable