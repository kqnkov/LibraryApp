package com.example.krasimiryankov.libraryapp.ui.registration

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.krasimiryankov.libraryapp.data.LibraryRepository
import com.example.krasimiryankov.libraryapp.model.Student

class RegistrationViewModel(private val repository: LibraryRepository) : ViewModel() {

    val students: LiveData<List<Student>> = repository.getStudents()

    fun addNewStudent(student: Student) {
        repository.addStudent(student)
    }

}