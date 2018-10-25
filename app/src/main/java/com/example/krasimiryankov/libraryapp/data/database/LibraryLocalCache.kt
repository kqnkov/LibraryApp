package com.example.krasimiryankov.libraryapp.data.database

import com.example.krasimiryankov.libraryapp.data.model.BookEntry
import com.example.krasimiryankov.libraryapp.data.model.Student
import java.util.concurrent.Executor

class LibraryLocalCache(
        private val bookDao: BookDao,
        private val studentDao: StudentDao,
        private val ioExecutor: Executor
){
    fun insertBooks(books: List<BookEntry>, insertFinished: ()->Unit){
        ioExecutor.execute{
            bookDao.insertAll(books)
            insertFinished()
        }
    }

    fun insertStudent(student: Student, insertFinished: () -> Unit){
        ioExecutor.execute {
            studentDao.insertStudent(student)
            insertFinished()
        }
    }

    fun getAllBooks(): List<BookEntry>{
        return bookDao.getAll()
    }
}