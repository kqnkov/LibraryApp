package com.example.krasimiryankov.libraryapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.krasimiryankov.libraryapp.data.BookConst
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.model.Student

@Database(
        version = 1,
        entities = [BookEntry::class, Student::class],
        exportSchema = false
)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(context: Context): LibraryDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: getBuiltDb(context).also { INSTANCE = it }
                }

        private fun getBuiltDb(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        LibraryDatabase::class.java,
                        BookConst.DATABASE_BOOKS)
                        .build()
    }
}