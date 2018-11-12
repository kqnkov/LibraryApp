package com.example.krasimiryankov.libraryapp.ui.registration

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.krasimiryankov.libraryapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegistrationActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(RegistrationActivity::class.java)

    @Test
    fun check_addNewStudent_Success() {
        val studentName = "Name4"
        val university = "My University"

        onView(withId(R.id.et_student_name)).check(matches(isDisplayed()))
        onView(withId(R.id.et_university)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()))
        onView(withId(R.id.listStudents)).check(matches(isDisplayed()))

        onView(withId(R.id.et_student_name)).check(matches(withText("")))
        onView(withId(R.id.et_university)).check(matches(withText("")))

        onView(withId(R.id.et_student_name)).perform(typeText(studentName))
        onView(withId(R.id.et_university)).perform(typeText(university), closeSoftKeyboard())

        onView(withId(R.id.btn_register)).perform(click())

        onView(withId(R.id.listStudents)).perform(scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(studentName))))
        onView(withId(R.id.listStudents)).check(matches(hasDescendant(withText(studentName))))
    }


    @Test
    fun check_addNewStudent_displayStudentInfo_Success() {
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.addStudent)).perform(ViewActions.click())

        onView(withId(R.id.et_university)).perform(closeSoftKeyboard())
        pressBack()
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
    }

}