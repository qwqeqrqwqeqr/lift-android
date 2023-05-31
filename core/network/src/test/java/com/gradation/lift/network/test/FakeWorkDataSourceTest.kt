package com.gradation.lift.network.test

import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.network.fake.FakeWorkDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.test.CoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FakeWorkDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: WorkDataSource
    @Before
    fun tearUp() {
    }

    @After
    fun tearDown() {}



    @Test
    fun testGetWorkPart() = runTest{

        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)

        assertEquals(
            dataSource.getWorkPart(),workPart1
        )

    }

    @Test
    fun testGetWorkCategory() = runTest{
        workDataSource.getWorkCategory()
    }

    @Test
    fun testGetWorkCategoryByWorkPart() = runTest{
        workDataSource.getWorkCategoryByWorkPart()
    }
}