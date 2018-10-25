package com.example.krasimiryankov.libraryapp.data.network

import com.example.krasimiryankov.libraryapp.data.model.BookEntry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("search")
    fun getBooks(@Query("query") query: String,
                 @Query("page") page: Int): Observable<List<BookEntry>>

}