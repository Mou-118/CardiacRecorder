package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest


public class MainActivityTest {

    @Rule
    public androidx.test.ext.junit.rules.ActivityScenarioRule<com.example.cardiacrecorder.MainActivity> activityRule =
            new androidx.test.ext.junit.rules.ActivityScenarioRule<>(com.example.cardiacrecorder.MainActivity.class);


    /**
     * UI Test for splash screen
     */


    @Test
    public void testSplash() {
        onView(withId(R.id.front)).check(matches(isDisplayed()));


    }

    public static class HomeTest {

        @Rule
        public androidx.test.ext.junit.rules.ActivityScenarioRule<HomeScreen> activityRule =
                new androidx.test.ext.junit.rules.ActivityScenarioRule<>(HomeScreen.class);

        /**
         * UI Test for main screen is displayed or not
         */


        @Test
        public void testHome() {

            onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()));
            onView(withId(R.id.homeScreen)).check(matches(isDisplayed()));
        }


        /**
         * UI Test for insertion of  a data
         */

        @Test
        public void testAdd(){

            onView(withId(R.id.floatingActionButton)).perform(click());
            onView(withId(R.id.entry)).check(matches(isDisplayed()));

            onView(withId(R.id.etDate)).perform(androidx.test.espresso.action.ViewActions.typeText("27-07-2022"));


            onView(withId(R.id.etTime)).perform(androidx.test.espresso.action.ViewActions.typeText("10:30"));


            onView(withId(R.id.etDiastolic)).perform(androidx.test.espresso.action.ViewActions.typeText("60"));


            onView(withId(R.id.etSystolic)).perform(androidx.test.espresso.action.ViewActions.typeText("100"));
           pressBack();

            onView(withId(R.id.etRate)).perform(androidx.test.espresso.action.ViewActions.typeText("72"));


            onView(withId(R.id.etComment)).perform(androidx.test.espresso.action.ViewActions.typeText("good"));
            pressBack();

            onView(withId(R.id.addButton)).perform(click());
            onView(withId(R.id.homeScreen)).check(matches(isDisplayed()));

        }

        /**
         * UI Test for delete
         */


        @Test
        public void testDelete(){

            onView(withId(R.id.deleteDataButton)).perform(click());
            onView(withId(R.id.homeScreen)).check(matches(isDisplayed()));

        }

        /**
         * UI Test for edit
         */

        @Test
        public void testedit(){

            onView(withId(R.id.editDataButton)).perform(click());


            onView(withId(R.id.upDia)).perform(ViewActions.clearText(),ViewActions.typeText("70"));
            pressBack();

            onView(withId(R.id.upButton)).perform(click());


        }

        /**
         * UI Test for view
         */

        @Test
        public void testView(){

            onView(withId(R.id.cardID)).perform(click());
            onView(withId(R.id.viewID)).check(matches(isDisplayed()));

        }


    }
}