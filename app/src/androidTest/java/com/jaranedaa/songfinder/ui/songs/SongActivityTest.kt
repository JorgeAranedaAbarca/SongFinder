package com.jaranedaa.songfinder.ui.songs

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jaranedaa.songfinder.R
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SongActivityTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(SongActivity::class.java, false, false)

    @Before
    fun setUp() {
        mActivityRule.launchActivity(createIntentWithName("Take the power back"))
    }

    @Test
    fun shouldShowListOfSongs() {
        onView(withId(R.id.rvSongs)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvSongs)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))


    }

    private fun createIntentWithName(name: String): Intent? {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        val intent = Intent(targetContext, SongActivity::class.java)
        intent.putExtra(ARG_NAME_SONG, name)
        return intent
    }

    companion object {
        const val ARG_NAME_SONG: String = "ARG_NAME_SONG"

    }
}