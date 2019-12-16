package com.jaranedaa.songfinder

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry

class Util {

    companion object {
        fun getResourceString(id: Int): String? {
            val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
            return targetContext.getResources().getString(id)
        }


        fun createIntentWithName(value: String, activity : Class<Activity>, key : String): Intent? {
            val targetContext: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
            val intent = Intent(targetContext, activity::class.java)
            intent.putExtra(key, value)
            return intent
        }
    }
}