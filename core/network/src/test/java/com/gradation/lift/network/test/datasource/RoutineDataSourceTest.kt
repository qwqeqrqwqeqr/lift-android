package com.gradation.lift.network.test.datasource

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.test.data.TestDefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.createRoutineSetRoutineModel
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.routineModelList
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.routineSetRoutineModelList
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RoutineDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: RoutineDataSource

    @Before
    fun tearUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testCreateRoutineSet() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(true),
            dataSource.createRoutineSet(createRoutineSetRoutine =  createRoutineSetRoutineModel).first()
        )
    }

    @Test
    fun testGetRoutine() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineModelList),
            dataSource.getRoutine().first()
        )
    }

    @Test
    fun testGetRoutineSetRoutine() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutine().first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineByWeekday() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByWeekday(FAKE_STRING_DATA).first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineByRoutineSetId() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByRoutineSetId(FAKE_INT_DATA).first()
        )
    }





}