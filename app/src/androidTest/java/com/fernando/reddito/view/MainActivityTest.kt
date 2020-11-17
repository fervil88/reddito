package com.fernando.reddito.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.fernando.reddito.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.ViewAction
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import org.hamcrest.Matcher
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.Espresso.onView
import org.hamcrest.CustomMatcher
import androidx.test.espresso.matcher.PreferenceMatchers.withTitle
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description


@LargeTest
class MainActivityTest {

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {

        // Starts the activity under test using
        // the default Intent with:
        // action = {@link Intent#ACTION_MAIN}
        // flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
        // All other fields are null or empty.
        /*mTestActivity = getActivity() as MainActivity
        mTestEmptyText = mTestActivity!!
            .findViewById<View>(R.id.empty) as TextView
        mFab = mTestActivity!!
            .findViewById(R.id.fab) as FloatingActionButton*/
    }

    /**
     * Test if your test fixture has been set up correctly.
     * You should always implement a test that
     * checks the correct setup of your test fixture.
     * If this tests fails all other tests are
     * likely to fail as well.
     */
    @Test
    fun testPreconditions() {
        // Try to add a message to add context to your assertions.
        // These messages will be shown if
        // a tests fails and make it easy to
        // understand why a test failed
        onView(withText("Detail Fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun testPostList() {
        onView(withId(R.id.drawer_layout))
            .check(matches(isDisplayed()))
            .check(matches(DrawerMatchers.isClosed()))
            .perform(DrawerActions.open())
            .check(matches(DrawerMatchers.isOpen()))
            .perform(ViewActions.swipeDown())

        Thread.sleep(2000)

        onView(withId(R.id.navigation_recycler_view))
            .check(matches(isDisplayed()))

        /*onView(withId(R.id.navigation_recycler_view))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))*/

        val matcher = withTitle("Avert your eyes boys")
        onView(withId(R.id.navigation_recycler_view)).perform(
            scrollToHolder(matcher),
            actionOnHolderItem(matcher, click())
        )

    }


    private fun withTitle(title: String): Matcher<RecyclerView.ViewHolder> {
        return object : BoundedMatcher<RecyclerView.ViewHolder,PostViewAdapter.PostViewHolder>(
            PostViewAdapter.PostViewHolder::class.java
        ) {
            override fun matchesSafely(item: PostViewAdapter.PostViewHolder): Boolean {
                return item.itemView.findViewById<TextView>(R.id.title_text_item).text.toString().equals(title, true)
            }

            override fun describeTo(description: Description) {
                description.appendText("view holder with title: $title")
            }
        }
    }

}