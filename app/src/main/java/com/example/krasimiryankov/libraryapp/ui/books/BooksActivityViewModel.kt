package com.example.krasimiryankov.libraryapp.ui.books

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.krasimiryankov.libraryapp.data.LibraryRepository
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.model.BooksResult

class BooksActivityViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val booksLiveData = MutableLiveData<String>()
    private val booksResult: LiveData<BooksResult> = Transformations.map(booksLiveData, { repository.getBooks(it) })

    val books: LiveData<PagedList<BookEntry>> = Transformations.switchMap(booksResult, { input -> input.data })
    val errors: LiveData<String> = Transformations.switchMap(booksResult, { input -> input.error })

    fun searchForTitle(query: String) {
        booksLiveData.postValue(query)
    }

}