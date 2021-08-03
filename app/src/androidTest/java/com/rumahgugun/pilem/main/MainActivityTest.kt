package com.rumahgugun.pilem.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.core.utils.DataDummy
import com.rumahgugun.pilem.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvs()
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
    private fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewMovies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun favoriteMoviesMechanism(){
        Espresso.onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.pressBack()
    }

    @Test
    fun favoriteTvsMechanism(){
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewTvs)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewTvs)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.action_bookmark)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.pressBack()
    }

    @Test
    fun loadTV() {
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewTvs))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recyclerViewTvs)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadMovieDetail() {
        Espresso.onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_detail_name_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_name_movie))
            .check(ViewAssertions.matches(withText(dummyMovie[0].name)))
        Espresso.onView(withId(R.id.tv_detail_description_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_description_movie))
            .check(ViewAssertions.matches(withText(dummyMovie[0].description)))
        Espresso.onView(withId(R.id.tv_detail_duration_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_duration_movie))
            .check(ViewAssertions.matches(withText(dummyMovie[0].duration)))
        Espresso.onView(withId(R.id.tv_detail_rating_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rating_movie))
            .check(ViewAssertions.matches(withText(dummyMovie[0].rating)))

    }
    @Test
    fun loadTVDetail() {

        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recyclerViewTvs)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_detail_name_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_name_tv))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].name)))
        Espresso.onView(withId(R.id.tv_detail_description_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_description_tv))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].description)))
        Espresso.onView(withId(R.id.tv_detail_duration_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_duration_tv))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].duration)))
        Espresso.onView(withId(R.id.tv_detail_rating_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rating_tv))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].rating)))


    }

}