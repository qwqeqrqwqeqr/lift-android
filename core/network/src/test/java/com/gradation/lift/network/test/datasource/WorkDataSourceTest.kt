package com.gradation.lift.network.test.datasource

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.fake.FakeWorkDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.workCategoryModelList
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.workPartModelList
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WorkDataSourceTest {

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
        assertEquals(APIResult.Success(workPartModelList),dataSource.getWorkPart().first())
    }

    @Test
    fun testGetWorkCategory() = runTest{
        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)
        assertEquals(APIResult.Success(workCategoryModelList),dataSource.getWorkCategory().first())
    }

    @Test
    fun testGetWorkCategoryByWorkPart() = runTest{
        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)
        assertEquals(APIResult.Success(workCategoryModelList),dataSource.getWorkCategoryByWorkPart(FAKE_INT_DATA).first())
    }
}