package com.example.krasimiryankov.libraryapp.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class BooksResult(
        val data: LiveData<PagedList<BookEntry>>,
        val error: LiveData<String>
)