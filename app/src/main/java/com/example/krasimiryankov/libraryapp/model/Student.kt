package com.example.krasimiryankov.libraryapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.krasimiryankov.libraryapp.data.BookConst

@Entity(tableName = BookConst.TABLE_STUDENT)
data class Student(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val name: String,
        val university: String?
)