package com.example.krasimiryankov.libraryapp.ui.students

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.squareup.picasso.Picasso

class StudentBooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.tv_book_title)
    private val price: TextView = view.findViewById(R.id.tv_book_price)
    private val image: ImageView = view.findViewById(R.id.iv_book_thumbnail)
    private val btnReturn: ImageView = view.findViewById(R.id.btnReturn)

    fun bind(item: BookEntry?, onReturnClickListener: (BookEntry) -> Unit) {
        title.text = item?.title.toString()
        price.text = item?.price.toString()
        btnReturn.setOnClickListener { onReturnClickListener(item!!) }
        Picasso.get().load(item?.image).into(image)
    }

    companion object {
        fun create(parent: ViewGroup): StudentBooksViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_book_to_return, parent, false)
            return StudentBooksViewHolder(view)
        }
    }
}