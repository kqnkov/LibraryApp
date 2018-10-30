package com.example.krasimiryankov.libraryapp.ui.registration

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.text.Editable
import android.text.TextWatcher
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.databinding.ActivityRegistrationBinding
import com.example.krasimiryankov.libraryapp.model.Student

class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: ActivityRegistrationBinding
    private val studentAdapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        viewModel = ViewModelProviders.of(this, Injection.provideRegisterViewModelFactory(this)).get(RegistrationViewModel::class.java)
        binding.viewModel = viewModel
        binding.student = Student()

        adapterSetup()
        attachTextWatchers()
    }

    private fun adapterSetup() {
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(itemDecoration)
        binding.list.adapter = studentAdapter

        viewModel.students.observe(this, Observer { list ->
            studentAdapter.updateList(list)
        })
    }

    private fun attachTextWatchers() {
        binding.etStudentName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.student?.name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        binding.etUniversity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.student?.university = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

    }
}
