package com.example.krasimiryankov.libraryapp.data.model

import android.arch.persistence.room.PrimaryKey

data class Student(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val name: String,
        val university: String?
)