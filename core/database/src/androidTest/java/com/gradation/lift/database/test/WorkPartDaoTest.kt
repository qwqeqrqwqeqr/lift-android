package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.database.Constants
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkPartDao
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
class WorkPartDaoTest {

    @Inject
    @Named(Constants.TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workPartDao: WorkPartDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        workPartDao = database.workPartDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

}

