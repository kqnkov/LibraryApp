package com.example.krasimiryankov.libraryapp.model

import android.arch.persistence.room.PrimaryKey
import java.sql.Date

data class StudentPersonalBooks(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val bookId: Int,
        val studentId: Int,
        val boughtDate: Date
)