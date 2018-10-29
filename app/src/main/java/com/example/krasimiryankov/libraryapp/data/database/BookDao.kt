package com.example.krasimiryankov.libraryapp.data.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.krasimiryankov.libraryapp.data.BookConst
import com.example.krasimiryankov.libraryapp.model.BookEntry

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<BookEntry>)

    @Query("SELECT * FROM " + BookConst.TABLE_BOOKS)
    fun getAll(): DataSource.Factory<Int, BookEntry>
}