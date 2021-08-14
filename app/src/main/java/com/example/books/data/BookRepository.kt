package com.example.books.data

import com.example.books.data.model.BooksResponse
import com.example.books.data.network.BookApiClient
import com.example.books.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api:BookApiClient,
    //private val request:SafeApiRequest
):BookRepo {
    override suspend fun getAllBooks(bookName: String): Resource<BooksResponse> {
        return withContext(Dispatchers.IO){
            try {
                Resource.Succes(api.getAllBooks(bookName))
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }
}
//    :SafeApiRequest(){

//    suspend fun getAllBooks(bookName:String) = apiRequest{
//        api.getAllBooks(bookName)
//    }

//}