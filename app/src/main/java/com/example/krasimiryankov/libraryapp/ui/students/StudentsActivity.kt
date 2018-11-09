package com.example.krasimiryankov.libraryapp.ui.students


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.krasimiryankov.libraryapp.Injection
import com.example.krasimiryankov.libraryapp.IntentConsts
import com.example.krasimiryankov.libraryapp.R
import com.example.krasimiryankov.libraryapp.model.BookEntry
import com.example.krasimiryankov.libraryapp.model.Student
import com.example.krasimiryankov.libraryapp.ui.books.BooksActivity
import com.example.krasimiryankov.libraryapp.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_students.*


class StudentsActivity : AppCompatActivity() {

    private var student: Student? = null
    private lateinit var studentsViewModel: StudentsViewModel
    private val adapter = StudentBooksAdapter { item: BookEntry -> onBookClicked(item) }
    private val noStudentSnackbar: Snackbar by lazy {
        Snackbar.make(tvStudentName, getString(R.string.message_student_not_found), Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        setSupportActionBar(toolbar)


        listBooks.adapter = adapter
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        listBooks.addItemDecoration(decoration)

        studentsViewModel = ViewModelProviders.of(this, Injection.provideStudentsViewModelFactory(this)).get(StudentsViewModel::class.java)

        studentsViewModel.studentDataResult.observe(this, Observer {
            if (it == null) {
                showInfoGroup(false)
                student = null
            } else {
                showInfoGroup(true)
                student = it
            }
            tvStudentName.text = it?.name
            tvStudentUniversity.text = it?.university
        })

        studentsViewModel.booksResult.observe(this, Observer {
            if (it == null) {
                listBooks.visibility = View.GONE
            } else {
                if (it.isEmpty()) {
                    listBooks.visibility = View.GONE
                } else {
                    listBooks.visibility = View.VISIBLE
                }
            }

            adapter.updateList(it)
        })

        attachTextWatchers()

        btnAddBook.setOnClickListener { _ ->
            if (!tvStudentName.text.isNullOrEmpty()) {
                loadUnusedBooks()
            } else {
                noStudentSnackbar.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.students_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.addStudent) {

            goToRegistration()
            return true
        }
        return super.onOptionsItemSelected(item)
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

    private fun loadUnusedBooks() {
        val intent = Intent(this, BooksActivity::class.java)
        intent.putExtra(IntentConsts.EXTRA_STUDENT_ID, student!!.id)
        startActivity(intent)
    }

    private fun onBookClicked(book: BookEntry) {
        studentsViewModel.returnBook(book)
    }

    private fun showInfoGroup(isFound: Boolean) {
        if (!isFound) {
            tvUserNotFound.visibility = View.VISIBLE
            tvStudentNameLabel.visibility = View.GONE
            tvStudentName.visibility = View.GONE
            tvStudentUniversity.visibility = View.GONE
            tvStudentUniversityLabel.visibility = View.GONE
            tvBookListLabel.visibility = View.GONE
            btnAddBook.visibility = View.GONE
        } else {
            tvUserNotFound.visibility = View.GONE
            tvStudentNameLabel.visibility = View.VISIBLE
            tvStudentName.visibility = View.VISIBLE
            tvStudentUniversity.visibility = View.VISIBLE
            tvStudentUniversityLabel.visibility = View.VISIBLE
            tvBookListLabel.visibility = View.VISIBLE
            btnAddBook.visibility = View.VISIBLE
        }
    }

    private fun goToRegistration() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == Activity.RESULT_OK && requestCode == BOOK_REQUEST_CODE) {
//            val bookId = data?.getIntExtra("book_id", -1)
//            if (bookId != -1) vie
//        }
//    }
}
