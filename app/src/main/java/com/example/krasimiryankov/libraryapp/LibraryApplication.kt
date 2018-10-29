package com.example.krasimiryankov.libraryapp

import android.app.Application
import com.facebook.stetho.Stetho

class LibraryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}