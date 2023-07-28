package com.gradation.lift.network.test.datasource

import com.gradation.lift.model.common.Weekday
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.routineModelList
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RoutineDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: RoutineDataSource



    @Test
    fun testCreateRoutineSetDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(true),
            dataSource.createRoutineSet(createRoutineSetRoutine =  createRoutineSetRoutineModel).first()
        )
    }

    @Test
    fun testGetRoutineDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineModelList),
            dataSource.getRoutine().first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutine().first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineByWeekdayDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByWeekday(Weekday.Monday()).first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineByRoutineSetIdDataSource() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)

        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByRoutineSetId(setOf(12,13,14)).first()
        )
    }





}