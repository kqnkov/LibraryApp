package com.example.krasimiryankov.libraryapp.data

import android.arch.paging.LivePagedListBuilder
import com.example.krasimiryankov.libraryapp.data.database.LibraryLocalCache
import com.example.krasimiryankov.libraryapp.data.network.BookApi
import com.example.krasimiryankov.libraryapp.data.paging.BookBoundaryCallback
import com.example.krasimiryankov.libraryapp.model.BooksResult

class BookRepository(
        private val cache: LibraryLocalCache,
        private val service: BookApi
) {
    fun getBooks(query: String): BooksResult {
        val dataSourceFactory = cache.getAllBooks()
        val boundaryCallback = BookBoundaryCallback(query, cache, service)
        val errors = boundaryCallback.errors
        val data = LivePagedListBuilder(dataSourceFactory, 20).setBoundaryCallback(boundaryCallback).build()

        return BooksResult(data, errors)
    }
}