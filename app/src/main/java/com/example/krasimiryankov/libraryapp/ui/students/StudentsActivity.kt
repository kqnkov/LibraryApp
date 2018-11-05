package com.example.krasimiryankov.libraryapp.ui.students


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.databinding.ActivityStudentsBinding
import com.example.krasimiryankov.libraryapp.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_students.*

class StudentsActivity : AppCompatActivity() {

    private lateinit var studentsViewModel: StudentsViewModel
    private val adapter = StudentBooksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStudentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_students)

        listBooks.adapter = adapter

        studentsViewModel = ViewModelProviders.of(this, Injection.provideStudentsViewModelFactory(this)).get(StudentsViewModel::class.java)

        studentsViewModel.studentDataResult.observe(this, Observer {
            tvStudentName.text = it?.name
            tvStudentUniversity.text = it?.university
        })

        studentsViewModel.booksResult.observe(this, Observer {
            adapter.updateList(it)
        })

        attachTextWatchers()

        binding.btnRegister.setOnClickListener { _ ->
            loadRegistration()
        }
    }

    private fun attachTextWatchers() {
        searchStudent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                studentsViewModel.searchStudent(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun loadRegistration() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}
