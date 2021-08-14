package com.example.books.data.network

import com.example.books.data.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiClient {

@GET("volumes")
suspend fun getAllBooks(@Query("q") bookName:String):BooksResponse

}