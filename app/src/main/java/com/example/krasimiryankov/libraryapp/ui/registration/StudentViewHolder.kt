package com.example.krasimiryankov.libraryapp.ui.registration

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.Student

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.tv_student_name)
    private val university: TextView = view.findViewById(R.id.tv_student_university)

    fun bind(item: Student?, onReturnClickListener: (Student) -> Unit) {
        name.text = item?.name.toString()
        university.text = item?.university.toString()
    }

    companion object {
        fun create(parent: ViewGroup): StudentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_student, parent, false)
            return StudentViewHolder(view)
        }
    }
}
