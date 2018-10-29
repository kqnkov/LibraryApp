package com.example.krasimiryankov.libraryapp.ui.books

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry

class BooksActivity : AppCompatActivity() {

    private lateinit var viewModel: BooksActivityViewModel
    private val adapter = BookAdapter()
    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.list)

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this)).get(BooksActivityViewModel::class.java)
        list.adapter = adapter

        viewModel.books.observe(this, Observer<PagedList<BookEntry>> {
            adapter.submitList(it)
        })

        viewModel.errors.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.searchForTitle("java")
    }

}