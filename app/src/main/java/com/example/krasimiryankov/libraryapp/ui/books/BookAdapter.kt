package com.example.krasimiryankov.libraryapp.ui.books

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.krasimiryankov.libraryapp.model.BookEntry


class BookAdapter(private val clickListener: (BookEntry) -> Unit) : PagedListAdapter<BookEntry, RecyclerView.ViewHolder>(BOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as BookViewHolder).bind(repoItem, clickListener)
        }
    }

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<BookEntry>() {
            override fun areItemsTheSame(oldItem: BookEntry, newItem: BookEntry): Boolean =
                    oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: BookEntry, newItem: BookEntry): Boolean =
                    oldItem == newItem
        }
    }
}