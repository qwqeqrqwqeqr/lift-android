package com.gradation.lift.network.test

import com.gradation.lift.network.datasource.RoutineDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class FakeWorkDataSourceTest {


    @Inject
    private lateinit var routineDataSource: RoutineDataSource


    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun tearUp() {

    }

    @After
    fun tearDown() {

    }



    @Test
    fun testInsertRoutineSet() = runTest{

    }
}