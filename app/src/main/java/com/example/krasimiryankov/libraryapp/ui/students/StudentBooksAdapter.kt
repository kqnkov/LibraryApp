package com.example.krasimiryankov.libraryapp.ui.students

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.krasimiryankov.libraryapp.databinding.RowBookBinding
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.squareup.picasso.Picasso

class StudentBooksAdapter : RecyclerView.Adapter<StudentBooksAdapter.ViewHolder>() {
    private var items: List<BookEntry>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowBookBinding.inflate(inflater)
        return (ViewHolder(binding))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    inner class ViewHolder(val binding: RowBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookEntry?) {
            binding.tvBookTitle.text = item?.title.toString()
            binding.tvBookPrice.text = item?.price.toString()
            Picasso.get().load(item?.image).into(binding.ivBookThumbnail)
        }
    }

    fun updateList(list: List<BookEntry>?) {
        items = list
        notifyDataSetChanged()
    }
}

