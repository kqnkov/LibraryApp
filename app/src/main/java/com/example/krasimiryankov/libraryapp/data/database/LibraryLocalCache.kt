package com.example.krasimiryankov.libraryapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.model.Student
import java.util.concurrent.Executor

class LibraryLocalCache(
        private val bookDao: BookDao,
        private val studentDao: StudentDao,
        private val ioExecutor: Executor
) {
    fun insertBooks(books: List<BookEntry>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            bookDao.insertAll(books)
            insertFinished()
        }
    }

    fun insertStudent(student: Student) {
        ioExecutor.execute {
            studentDao.insertStudent(student)
        }
    }

    fun getAllBooks(): DataSource.Factory<Int, BookEntry> {
        return bookDao.getAll()
    }

    fun getAllStudents(): LiveData<List<Student>> {
        return studentDao.getAllStudents()
    }

    fun getStudentByName(name: String): LiveData<Student>{
        return studentDao.getStudentByName(name)
    }

    fun getStudentBooks(studentId: Int?): LiveData<List<BookEntry>>{
        return bookDao.getStudentBooks(studentId)
    }
}