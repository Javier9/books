package com.example.books.data.model

data class BooksResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)