package com.gradation.lift.network.test.datasource

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.fake.FakeWorkDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.data_generator.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.data_generator.ModelDataGenerator.WorkCategory.workCategoryModelList
import com.gradation.lift.model.data_generator.ModelDataGenerator.WorkPart.workPartModelList
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WorkDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: WorkDataSource



    @Test
    fun testGetWorkPartDataSource() = runTest{
        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)
        assertEquals(APIResult.Success(workPartModelList),dataSource.getWorkPart().first())
    }

    @Test
    fun testGetWorkCategoryDataSource() = runTest{
        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)
        assertEquals(APIResult.Success(workCategoryModelList),dataSource.getWorkCategory().first())
    }

    @Test
    fun testGetWorkCategoryByWorkPartDataSource() = runTest{
        dataSource = FakeWorkDataSource(testReturnState=TestReturnState.Success)
        assertEquals(APIResult.Success(workCategoryModelList),dataSource.getWorkCategoryByWorkPart(
            FAKE_STRING_DATA).first())
    }
}