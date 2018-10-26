package com.example.krasimiryankov.libraryapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.ui.BookAdapter
import com.example.krasimiryankov.libraryapp.ui.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val adapter = BookAdapter()
    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.list)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        list.adapter = adapter

        viewModel.books.observe(this, Observer<PagedList<BookEntry>> {
            adapter.submitList(it)
        })

        viewModel.errors.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.searchForTitle("java")
        adapter.submitList(null)
    }

}