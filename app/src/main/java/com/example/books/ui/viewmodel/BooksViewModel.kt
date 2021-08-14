package com.example.books.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books.data.BookRepo
import com.example.books.data.BookRepository
import com.example.books.data.model.BooksResponse
import com.example.books.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BookRepo
) : ViewModel() {

    private val _book = MutableLiveData<Resource<BooksResponse>>()
    val book : LiveData<Resource<BooksResponse>>
    get() = _book

    init {
        getBooks("Internet")
    }

    fun getBooks(bookName:String) = viewModelScope.launch{
        _book.value = repository.getAllBooks(bookName)
    }

}