package com.example.krasimiryankov.libraryapp.ui.registration

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.krasimiryankov.libraryapp.model.Student

class StudentAdapter(val studentClickListener: (Student) -> Unit) : RecyclerView.Adapter<StudentViewHolder>() {
    private var items: List<Student>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return (StudentViewHolder.create(parent))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(items?.get(position), studentClickListener)
    }

    fun updateList(list: List<Student>?) {
        items = list
        notifyDataSetChanged()
    }
}

