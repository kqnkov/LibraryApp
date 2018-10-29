package com.example.krasimiryankov.libraryapp.ui.students

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.databinding.ActivityStudentsBinding
import com.example.krasimiryankov.libraryapp.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_students.*

class StudentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStudentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_students)

        binding.btnRegister.setOnClickListener { _ ->
            loadRegistration()
        }
    }

    private fun loadRegistration() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}
