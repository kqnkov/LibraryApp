package com.example.krasimiryankov.libraryapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.example.krasimiryankov.libraryapp.data.BookConst

@Entity(tableName = BookConst.TABLE_STUDENT)
data class Student(
        @PrimaryKey(autoGenerate = true) val id: Int,
        var name: String,
        var university: String
) {
    @Ignore constructor() : this(0, "", "")
}
