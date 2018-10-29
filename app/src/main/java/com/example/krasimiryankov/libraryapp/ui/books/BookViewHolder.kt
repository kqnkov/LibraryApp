package com.example.krasimiryankov.libraryapp.ui.books

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry

class BookViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val title: TextView = view.findViewById(R.id.tv_book_title)
    private val price: TextView = view.findViewById(R.id.tv_book_price)

    init {

    }

    fun bind (book: BookEntry?){
        if (book != null){
            displayBookData(book)
        }
    }

    fun displayBookData(book: BookEntry?){
        title.text = book?.title.toString()
        price.text = book?.price.toString()
    }


    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
            return BookViewHolder(view)
        }
    }

}