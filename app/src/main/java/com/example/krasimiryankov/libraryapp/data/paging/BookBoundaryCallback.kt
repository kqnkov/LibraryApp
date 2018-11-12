package com.example.krasimiryankov.libraryapp.data.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.example.krasimiryankov.libraryapp.data.database.LibraryLocalCache
import com.example.krasimiryankov.libraryapp.data.network.BookApi
import com.example.krasimiryankov.libraryapp.data.network.getBooksFromApi
import com.example.krasimiryankov.libraryapp.model.BookEntry
import java.util.*

class BookBoundaryCallback(
        private val query: String,
        private val cache: LibraryLocalCache,
        private val service: BookApi
) : PagedList.BoundaryCallback<BookEntry>() {
    private var lastRequestedPage = 1

    val _errors = MutableLiveData<String>()
    val errors: LiveData<String>
        get() = _errors

    override fun onZeroItemsLoaded() {
        updateRecentData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: BookEntry) {
        updateRecentData(query)
    }

    private fun updateRecentData(query: String) {
        getBooksFromApi(service, query, lastRequestedPage, { books ->
            cache.insertBooks(books, {
                lastRequestedPage++
            })
        }, { error ->
            _errors.postValue(error)
        })
    }

    fun IntRange.random() =
            Random().nextInt((endInclusive + 1) - start) + start
}