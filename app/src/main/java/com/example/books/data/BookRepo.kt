package com.example.books.data

import com.example.books.data.model.BooksResponse
import com.example.books.vo.Resource

interface BookRepo {
    suspend fun getAllBooks(bookName:String): Resource<BooksResponse>
}