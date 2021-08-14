package com.example.books.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Epub(
    val acsTokenLink: String,
    val downloadLink: String,
    val isAvailable: Boolean
):Parcelable