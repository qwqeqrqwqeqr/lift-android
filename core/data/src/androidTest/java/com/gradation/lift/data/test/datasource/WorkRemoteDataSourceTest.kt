package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeWorkRemoteDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.WORK_CATEGORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.WorkPart.WORK_PART_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WorkRemoteDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: WorkRemoteDataSource


    @Test
    fun getWorkPartDataSource() = runTest {
        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Success)
        assertEquals(
            NetworkResult.Success(listOf(WORK_PART_MODEL)),
            dataSource.getWorkPart().first()
        )

        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Fail)

        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getWorkPart().first()
        )
    }

    @Test
    fun getWorkCategoryDataSource() = runTest {
        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Success)
        assertEquals(
            NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            dataSource.getWorkCategory().first()
        )

        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Fail)

        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getWorkCategory().first()
        )
    }

    @Test
    fun getWorkCategoryByWorkPartDataSource() = runTest {
        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Success)
        assertEquals(
            NetworkResult.Success(listOf(WORK_CATEGORY_MODEL)),
            dataSource.getWorkCategoryByWorkPart(FAKE_STRING_DATA).first()
        )

        dataSource =
            FakeWorkRemoteDataSource(testReturnState = TestReturnState.Fail)

        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getWorkCategory().first()
        )
    }
}