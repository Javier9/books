package com.example.books.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchInfo(
    val textSnippet: String
):Parcelable