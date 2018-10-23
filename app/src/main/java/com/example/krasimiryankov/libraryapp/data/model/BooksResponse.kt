package com.example.krasimiryankov.libraryapp.data.model

import android.arch.lifecycle.LiveData
import com.google.gson.annotations.SerializedName

data class BooksResponse(
        @SerializedName("total") val total: Int,
        @SerializedName("page") val page: Int,
        @SerializedName("books") val books: LiveData<List<BookEntry>>
)