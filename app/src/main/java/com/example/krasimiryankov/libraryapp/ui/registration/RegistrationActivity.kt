package com.example.krasimiryankov.libraryapp.ui.registration

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.databinding.ActivityRegistrationBinding
import com.example.krasimiryankov.libraryapp.model.Student

class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        viewModel = ViewModelProviders.of(this, Injection.provideRegisterViewModelFactory(this)).get(RegistrationViewModel::class.java)
        viewModel.addNewStudent(student = Student(1, "Gosho", "TU"))
        viewModel.students.observe(this, Observer { _ ->
            Toast.makeText(this, "Student inserted", Toast.LENGTH_SHORT).show()
        })
    }
}
