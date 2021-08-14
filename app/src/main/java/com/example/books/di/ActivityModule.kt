package com.example.books.di

import com.example.books.data.BookRepo
import com.example.books.data.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImp(repoImp: BookRepository):BookRepo
}