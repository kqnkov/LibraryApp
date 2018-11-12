package com.example.krasimiryankov.libraryapp.ui.students

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.krasimiryankov.libraryapp.data.LibraryRepository
import com.example.krasimiryankov.libraryapp.model.BookEntry

class StudentsViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val studentNameStream = MutableLiveData<String>()

    val studentDataResult = Transformations.switchMap(studentNameStream) { repository.getStudentByName(it) }

    val booksResult = Transformations.switchMap(studentDataResult) {
        repository.getStudentBooks(it?.id)
    }

    fun returnBook(book: BookEntry) {
        book.studentId = null
        repository.updateBook(book)
    }

    fun searchStudent(string: String) {
        studentNameStream.value = string
    }

}