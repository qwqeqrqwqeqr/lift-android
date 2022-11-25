package com.gradation.lift.database.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.database.util.TestDataGenerator
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

class WorkCategoryDaoTest {

    @Inject
    @Named("test_database")
    lateinit var database: LiftDatabase
    private lateinit var workCategoryDao: WorkCategoryDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
        workCategoryDao = database.workCategoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


  
}