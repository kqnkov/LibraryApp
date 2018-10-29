package com.example.krasimiryankov.libraryapp

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.krasimiryankov.libraryapp.data.LibraryRepository
import com.example.krasimiryankov.libraryapp.data.database.LibraryDatabase
import com.example.krasimiryankov.libraryapp.data.database.LibraryLocalCache
import com.example.krasimiryankov.libraryapp.data.network.BookApi
import com.example.krasimiryankov.libraryapp.ui.books.ViewModelFactory
import com.example.krasimiryankov.libraryapp.ui.registration.RegisterViewModelFactory

import java.util.concurrent.Executors


object Injection {

    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): LibraryLocalCache {
        val database = LibraryDatabase.getInstance(context)
        return LibraryLocalCache(database.bookDao(), database.studentDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(context: Context): LibraryRepository {
        return LibraryRepository(provideCache(context), BookApi.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }

    fun provideRegisterViewModelFactory(context: Context): ViewModelProvider.Factory {
        return RegisterViewModelFactory(provideGithubRepository(context))
    }
}