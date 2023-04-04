package com.aneke.peter.whatsapplinker

import android.content.Intent
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.UriMatchers.hasHost
import androidx.test.espresso.intent.matcher.UriMatchers.hasScheme
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    //Set code to nigeria because no espresso method to scroll the CountryCodeSpinner as of ye

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun checkIfMainActivityIsVisible() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    @Test
    fun typeWrongPhoneNo_ExpectButtonDisabled() {
        onView(withId(R.id.textInput))
            .perform(typeText("Hello World"), closeSoftKeyboard())

        onView(withId(R.id.link_text)).check(matches(not(isDisplayed())))
    }

    @Test
    fun selectNigeriaCode_typeCorrectPhoneNo_ExpectButtonDisabled() {
        onView(withId(R.id.textInput))
            .perform(typeText("9034014512"), closeSoftKeyboard())

        onView(withId(R.id.link_text)).check(matches(isDisplayed()))
    }

    @Test
    fun selectNigeriaCode_typeCorrectPhoneNo_NavigateIfWhatsappInstalled_ExpectButtonDisabled() {
        onView(withId(R.id.textInput))
            .perform(typeText("9034014512"), closeSoftKeyboard())
        onView(withId(R.id.link_text)).perform(click())

        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(hasScheme("https")),
                hasData(hasHost("wa.me")),
            )
        )
    }
}