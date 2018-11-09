package com.example.krasimiryankov.libraryapp.data

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import com.example.krasimiryankov.libraryapp.data.database.LibraryLocalCache
import com.example.krasimiryankov.libraryapp.data.network.BookApi
import com.example.krasimiryankov.libraryapp.data.paging.BookBoundaryCallback
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.model.BooksResult
import com.example.krasimiryankov.libraryapp.model.Student

class LibraryRepository(
        private val cache: LibraryLocalCache,
        private val service: BookApi
) {
    fun getBooks(query: String): BooksResult {
        val dataSourceFactory = cache.getAllBooks()
        val boundaryCallback = BookBoundaryCallback(query, cache, service)
        val errors = boundaryCallback.errors
        val data = LivePagedListBuilder(dataSourceFactory, BOOKS_ITEMS_PER_PAGE).setBoundaryCallback(boundaryCallback).build()

        return BooksResult(data, errors)
    }

    fun addStudent(student: Student) {
        cache.insertStudent(student)
    }

    fun getStudents(): LiveData<List<Student>> {
        return cache.getAllStudents()
    }

    fun getStudentByName(name: String): LiveData<Student> {
        return cache.getStudentByName(name)
    }

    fun getStudentBooks(id: Int?): LiveData<List<BookEntry>> {
        return cache.getStudentBooks(id)
    }

    fun updateBook(bookEntry: BookEntry) {
        cache.updateBook(bookEntry)
    }

    companion object {
        const val BOOKS_ITEMS_PER_PAGE = 20
    }
}