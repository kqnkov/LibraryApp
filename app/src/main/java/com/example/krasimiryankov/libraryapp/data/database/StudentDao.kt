package com.example.krasimiryankov.libraryapp.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.krasimiryankov.libraryapp.data.BookConst
import com.example.krasimiryankov.libraryapp.model.Student


@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    @Query("SELECT * FROM " + BookConst.TABLE_STUDENT + " WHERE id == :id")
    fun getStudentById(id: Long): Student

}