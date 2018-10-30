package com.example.krasimiryankov.libraryapp.ui.registration

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.krasimiryankov.libraryapp.databinding.RowStudentBinding
import com.example.krasimiryankov.libraryapp.model.Student

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private var items: List<Student>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowStudentBinding.inflate(inflater)
        return (ViewHolder(binding))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    inner class ViewHolder(val binding: RowStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student?) {
            binding.tvStudentName.text = item?.name
            binding.tvStudentUniversity.text = item?.university
        }
    }

    fun updateList(list: List<Student>?) {
        items = list
        notifyDataSetChanged()
    }
}