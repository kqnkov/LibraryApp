package com.example.krasimiryankov.libraryapp.ui.students

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.example.krasimiryankov.libraryapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class StudentsActivityTest {


    @get:Rule
    val activityRule = ActivityTestRule(StudentsActivity::class.java)

    @Test
    fun check_searchViewIsDisplayed() {
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
    }

    @Test
    fun check_searchViewIsDisplayed_inputSuccess() {
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.searchStudent)).perform(typeText("John"))
    }

    @Test
    fun check_inputSearch_displaysUserInfo_Success() {
        val studentName = "John"
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.searchStudent)).perform(typeText(studentName))

        onView(withId(R.id.tvStudentName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentName)).check(matches(withText(studentName)))

        onView(withId(R.id.tvStudentNameLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookListLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentUniversity)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentUniversityLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.tvUserNotFound)).check(matches(not(isDisplayed())))

        onView(withId(R.id.listBooks)).check(matches(isDisplayed()))
    }

    @Test
    fun check_inputSearch_displaysUserInfo_NotFound() {
        val studentName = "James"
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.searchStudent)).perform(typeText(studentName))

        onView(withId(R.id.tvUserNotFound)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStudentNameLabel)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tvBookListLabel)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tvStudentUniversity)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tvStudentUniversityLabel)).check(matches(not(isDisplayed())))

        onView(withId(R.id.listBooks)).check(matches(not(isDisplayed())))
    }

    @Test
    fun check_addNewStudent_displayStudentInfoWithoutBooks_Success() {
        val studentName = "Name10"
        val university = "My University"

        //Perform navigation action from StudentsActivity
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.addStudent)).perform(ViewActions.click())


        //Perform adding a new student and check if the operation was successfully
        onView(withId(R.id.et_student_name)).check(matches(isDisplayed()))
        onView(withId(R.id.et_university)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()))
        onView(withId(R.id.listStudents)).check(matches(isDisplayed()))

        onView(withId(R.id.et_student_name)).check(matches(withText("")))
        onView(withId(R.id.et_university)).check(matches(withText("")))

        onView(withId(R.id.et_student_name)).perform(typeText(studentName))
        onView(withId(R.id.et_university)).perform(typeText(university), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btn_register)).perform(ViewActions.click())

        onView(withId(R.id.listStudents)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(studentName))))
        onView(withId(R.id.listStudents)).check(matches(hasDescendant(withText(studentName))))

        //Navigating back actions
        onView(withId(R.id.et_student_name)).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.et_university)).perform(ViewActions.closeSoftKeyboard())
        Espresso.pressBack()

        //Perform check for displaying student's data for the recently added person
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.searchStudent)).perform(typeText(studentName))

        onView(withId(R.id.tvStudentName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentName)).check(matches(withText(studentName)))

        onView(withId(R.id.tvStudentNameLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvBookListLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentUniversity)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentUniversityLabel)).check(matches(isDisplayed()))

        onView(withId(R.id.tvUserNotFound)).check(matches(not(isDisplayed())))

        onView(withId(R.id.listBooks)).check(matches(not(isDisplayed())))
    }

    @Test
    fun check_findStudent_addingBooksToStudent_Success() {
        val studentName = "Name4"

        //Initial checks in StudentsActivity
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.btnAddBook)).check(matches(not(isDisplayed())))

        onView(withId(R.id.searchStudent)).perform(typeText(studentName), closeSoftKeyboard())
        onView(withId(R.id.searchStudent)).check(matches(TextLength3SymbolsMatcher.withCustomMatch()))
        onView(withId(R.id.tvStudentName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentName)).check(matches(withText(studentName)))

        onView(withId(R.id.btnAddBook)).check(matches(isDisplayed()))

        onView(withId(R.id.btnAddBook)).perform(click())

        //Initial checks in BooksActivity
        onView(withId(R.id.listBooks)).check(matches(isDisplayed()))
        onView(withId(R.id.listBooks)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.pressBack()

        //Return to StudentsActivity and check the view states and is newly added book is related with the student
        onView(withId(R.id.tvStudentName)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStudentUniversity)).check(matches(isDisplayed()))
        onView(withId(R.id.addStudent)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStudentName)).check(matches(withText(studentName)))
        onView(withId(R.id.listBooks)).perform(RecyclerViewScrollToLastAction())
        //onView(withId(R.id.listStudents)).check(matches(hasDescendant(withText(""))))

        onView(withId(R.id.tvUserNotFound)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.listBooks)).perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(studentName))))
//        onView(withId(R.id.listBooks)).check(matches(hasDescendant(withText(studentName))))
    }

    class TextLength3SymbolsMatcher {
        companion object {
            fun withCustomMatch(): Matcher<View> {
                return object : BoundedMatcher<View, EditText>(EditText::class.java) {

                    override fun describeTo(description: Description?) {
                        description?.appendText("Checks if the edit text field has more than 3 symbols typed.")
                    }

                    override fun matchesSafely(item: EditText?): Boolean {
                        return item?.text.toString().length > 3
                    }
                }
            }
        }
    }

    class RecyclerViewScrollToLastAction : ViewAction {
        override fun getDescription(): String {
            return "Scroll the given RecyclerView to it's last position"
        }

        override fun getConstraints(): Matcher<View> {
            return isDisplayed()
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            recyclerView.smoothScrollToPosition(recyclerView.adapter.itemCount)
        }

    }
}