package com.example.krasimiryankov.libraryapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.krasimiryankov.libraryapp.data.BookConst
import com.google.gson.annotations.SerializedName

@Entity(tableName = BookConst.TABLE_BOOKS)
data class BookEntry(
        @PrimaryKey(autoGenerate = true)
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("subtitle") val subtitle: String,
        @field:SerializedName("isbn13") val isbn: String,
        @field:SerializedName("price") val price: String,
        @field:SerializedName("image") val image: String,
        @field:SerializedName("url") val url: String
)