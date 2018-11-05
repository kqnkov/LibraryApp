package com.example.krasimiryankov.libraryapp.model

import android.arch.lifecycle.LiveData

data class StudentBooksResult(
        val studentData: LiveData<Student>,
        val booksData: LiveData<List<BookEntry>>
)