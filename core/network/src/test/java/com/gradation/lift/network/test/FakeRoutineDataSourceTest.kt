package com.gradation.lift.network.test

import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.test.data.TestModelDataGenerator.FAKE_CREATE_ROUTINE_SET
import com.gradation.lift.test.data.TestModelDataGenerator.FAKE_DATE_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.FAKE_INT_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.test.data.TestModelDataGenerator.routineModelList
import com.gradation.lift.test.data.TestModelDataGenerator.routineSetModel1
import com.gradation.lift.test.data.TestModelDataGenerator.routineSetModelList
import com.gradation.lift.test.data.TestModelDataGenerator.routineSetRoutineModelList
import com.gradation.lift.network.utils.TestReturnState
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
class FakeRoutineDataSourceTest {

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
    fun testGetRoutineSet() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineSetModelList),
            dataSource.getRoutineSet(FAKE_STRING_DATA).first()
        )
    }


    @Test
    fun testCreateRoutineSet() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(true),
            dataSource.createRoutineSet(FAKE_CREATE_ROUTINE_SET).first()
        )
    }

    @Test
    fun testGetRoutineSetByDate() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineSetModelList),
            dataSource.getRoutineSetByDate(FAKE_STRING_DATA, FAKE_DATE_DATA).first()
        )
    }

    @Test
    fun testGetRoutineSetByRoutineSetId() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineSetModel1),
            dataSource.getRoutineSetByRoutineSetId(FAKE_STRING_DATA, FAKE_INT_DATA).first()
        )
    }

    @Test
    fun testGetRoutine() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineModelList),
            dataSource.getRoutine(FAKE_STRING_DATA).first()
        )
    }

    @Test
    fun testGetRoutineByDate() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineModelList), dataSource.getRoutineByDate(
                FAKE_STRING_DATA,
                FAKE_DATE_DATA
            ).first()
        )
    }

    @Test
    fun testGetRoutineByRoutineSetId() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineModelList), dataSource.getRoutineByRoutineSetId(
                FAKE_STRING_DATA,
                FAKE_INT_DATA
            ).first()
        )
    }

    @Test
    fun testGetRoutineByDateAndRoutineSetId() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineModelList), dataSource.getRoutineByDateAndRoutineSetId(
                FAKE_STRING_DATA,
                FAKE_DATE_DATA, FAKE_INT_DATA
            ).first()
        )
    }


    @Test
    fun testGetRoutineSetRoutine() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutine(FAKE_STRING_DATA).first()
        )
    }

    @Test
    fun testGetRoutineSetRoutineByDate() = runTest {
        dataSource = FakeRoutineDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            APIResult.Success(routineSetRoutineModelList),
            dataSource.getRoutineSetRoutineByDate(FAKE_STRING_DATA, FAKE_DATE_DATA).first()
        )
    }


}