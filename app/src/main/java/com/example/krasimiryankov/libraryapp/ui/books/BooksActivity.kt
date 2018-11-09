package com.example.krasimiryankov.libraryapp.ui.books

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.IntentConsts
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {

    private lateinit var viewModel: BooksActivityViewModel
    private val adapter = BookAdapter { item: BookEntry -> onBookClicked(item) }
    private var studentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setSupportActionBar(toolbarBooks)
        toolbarBooks.navigationIcon = getDrawable(R.drawable.ic_baseline_arrow_back)
        toolbarBooks.setNavigationOnClickListener { _ -> finish() }


        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        listBooks.addItemDecoration(decoration)


        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this)).get(BooksActivityViewModel::class.java)
        listBooks.adapter = adapter

        viewModel.books.observe(this, Observer<PagedList<BookEntry>> {
            adapter.submitList(it)
        })

        viewModel.errors.observe(this, Observer<String> {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.searchForTitle("java")
    }

    override fun onResume() {
        super.onResume()
        studentId = intent.getIntExtra(IntentConsts.EXTRA_STUDENT_ID, -1)
    }

    private fun onBookClicked(bookEntry: BookEntry) {
        if (studentId != -1) viewModel.addBookToStudent(studentId, bookEntry)
    }

}