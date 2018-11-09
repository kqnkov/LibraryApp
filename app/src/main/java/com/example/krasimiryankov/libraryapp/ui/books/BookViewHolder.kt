package com.example.krasimiryankov.libraryapp.ui.books

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book.view.*

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val itemContainer = view.itemContainer
    private val title: TextView = view.findViewById(R.id.tv_book_title)
    private val price: TextView = view.findViewById(R.id.tv_book_price)
    private val image: ImageView = view.findViewById(R.id.iv_book_thumbnail)

    fun bind(book: BookEntry?, itemCLickListener: (BookEntry) -> Unit) {
        if (book != null) {
            displayBookData(book, itemCLickListener)
        }
    }

    fun displayBookData(book: BookEntry?, itemCLickListener: (BookEntry) -> Unit) {
        itemContainer.setOnClickListener { itemCLickListener(book!!) }
        title.text = book?.title.toString()
        price.text = book?.price.toString()
        Picasso.get().load(book?.image).into(image)
    }


    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
            return BookViewHolder(view)
        }
    }

}