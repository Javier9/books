package com.example.books.di

import androidx.viewbinding.BuildConfig
import com.example.books.data.network.BookApiClient
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        try {

            var builder: OkHttpClient.Builder = OkHttpClient.Builder()

            builder.connectTimeout(300, TimeUnit.SECONDS)
            builder.readTimeout(80, TimeUnit.SECONDS)
            builder.writeTimeout(90, TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true)
            builder.addNetworkInterceptor(Interceptor {
                val request: Request = it.request().newBuilder().addHeader("Connection", "close").build()
                return@Interceptor it.proceed(request)
            })

            if (BuildConfig.DEBUG) {
                var loggingInterceptor =  HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .client(provideOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBooksApiClient(retrofit:Retrofit): BookApiClient {
        return retrofit.create(BookApiClient::class.java)
    }



}