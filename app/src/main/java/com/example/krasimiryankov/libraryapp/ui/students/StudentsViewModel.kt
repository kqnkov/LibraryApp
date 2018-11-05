package com.example.krasimiryankov.libraryapp.ui.students

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.krasimiryankov.libraryapp.data.LibraryRepository

class StudentsViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val studentNameStream = MutableLiveData<String>()

    val studentDataResult = Transformations.switchMap(studentNameStream) { repository.getStudentByName(it) }

    val booksResult = Transformations.switchMap(studentDataResult) {
        repository.getStudentBooks(it?.id)
    }

    fun searchStudent(string: String) {
        studentNameStream.value = string
    }

}