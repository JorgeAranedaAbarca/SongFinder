package com.jaranedaa.songfinder.ui.album


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

class AlbumActivityTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(AlbumActivity::class.java, false, false)

    @Before
    fun setUp() {
        mActivityRule.launchActivity(createIntentWithName("1072796439"))
    }

    @Test
    fun shouldShowAlbum() {
        onView(withId(R.id.rvAlbum)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvAlbum)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    private fun createIntentWithName(value: String): Intent? {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        val intent = Intent(targetContext, AlbumActivity::class.java)
        intent.putExtra(ARG_COLLECTION_ID, value)
        return intent
    }
    companion object {
        const val ARG_COLLECTION_ID: String = "ARG_COLLECTION_ID"

    }
}