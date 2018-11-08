package com.example.krasimiryankov.libraryapp.ui.students

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.krasimiryankov.libraryapp.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class StudentsActivityTest{


    @get:Rule
    val activityRule = ActivityTestRule(StudentsActivity::class.java)

    @Test
    fun check_searchViewIsDisplayed(){
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
    }

    @Test
    fun check_searchViewIsDisplayed_inputSuccess(){
        onView(withId(R.id.searchStudent)).check(matches(isDisplayed()))
        onView(withId(R.id.searchStudent)).perform(typeText("John"))
    }

    @Test
    fun check_inputSearch_displaysUserInfo_Success(){
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
    fun check_inputSearch_displaysUserInfo_NotFound(){
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
    fun check_addUserNavigation_Success(){

    }
}