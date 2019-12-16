package com.jaranedaa.songfinder.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.Util
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun debeMostrarEditText() {
        onView(withId(R.id.etSearch)).check(matches(isDisplayed()))
    }

    @Test
    fun debeMostrarBotonBuscar() {
        onView(withId(R.id.btnSearch)).check(matches(isDisplayed()))
    }

    @Test
    fun showErrorsEditTextIncompleted() {
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.etSearch))
            .check(matches(hasErrorText(Util.getResourceString(R.string.err_search))))
    }

}