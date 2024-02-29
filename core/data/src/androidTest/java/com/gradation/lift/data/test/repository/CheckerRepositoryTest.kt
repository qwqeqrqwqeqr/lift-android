package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.di.TestDispatcher
import com.gradation.lift.data.fake.datasource.FakeCheckerDataSource
import com.gradation.lift.data.repository.DefaultCheckerRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class CheckerRepositoryTest {

    private lateinit var failDataSource: CheckerDataSource
    private lateinit var successDataSource: CheckerDataSource
    private lateinit var successRepository: CheckerRepository
    private lateinit var failRepository: CheckerRepository
    private lateinit var testDispatcher: DispatcherProvider


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeCheckerDataSource(TestReturnState.Fail)
        successDataSource = FakeCheckerDataSource(TestReturnState.Success)
        testDispatcher = TestDispatcher.testDispatchers()

        successRepository = DefaultCheckerRepository(successDataSource, testDispatcher)
        failRepository = DefaultCheckerRepository(failDataSource, testDispatcher)
    }



    @Test
    fun checkDuplicateEmail() = runTest {
        Truth.assertThat(
            DataState.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            successRepository.checkDuplicateEmail(DefaultDataGenerator.FAKE_STRING_DATA).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.checkDuplicateEmail(DefaultDataGenerator.FAKE_STRING_DATA).first()
        )
    }

    @Test
    fun checkDuplicateName() = runTest {
        Truth.assertThat(
            DataState.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            successRepository.checkDuplicateEmail(DefaultDataGenerator.FAKE_STRING_DATA).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.checkDuplicateEmail(DefaultDataGenerator.FAKE_STRING_DATA).first()
        )
    }
}