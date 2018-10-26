package com.example.krasimiryankov.libraryapp.data.network

import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.google.gson.annotations.SerializedName

data class BooksResponse(
        @SerializedName("total") val total: Int,
        @SerializedName("page") val page: Int,
        @SerializedName("books") val books: List<BookEntry>
)