package com.gradation.lift.database

import androidx.test.filters.SmallTest
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RoutineSetDaoTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: LiftDatabase
    private lateinit var routineSetDao: RoutineSetDao


    @Before
    fun setup() {
        hiltRule.inject()
        routineSetDao = database.routineSetDao()
    }


    @After
    fun teardown() {
        database.close()
    }

}