package com.example.krasimiryankov.libraryapp.ui.students

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.krasimiryankov.libraryapp.model.BookEntry

class StudentBooksAdapter(val returnBookListener: (BookEntry) -> Unit) : RecyclerView.Adapter<StudentBooksViewHolder>() {
    private var items: List<BookEntry>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentBooksViewHolder {
        return (StudentBooksViewHolder.create(parent))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: StudentBooksViewHolder, position: Int) {
        holder.bind(items?.get(position), returnBookListener)
    }

    fun updateList(list: List<BookEntry>?) {
        items = list
        notifyDataSetChanged()
    }
}

