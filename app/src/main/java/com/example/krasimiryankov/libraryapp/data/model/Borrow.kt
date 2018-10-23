package com.example.krasimiryankov.libraryapp.data.model

import android.arch.persistence.room.PrimaryKey
import java.sql.Date

data class Borrow(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val bookId: Int,
        val studentId: Int,
        val startDate: Date,
        val endDate: Date
)