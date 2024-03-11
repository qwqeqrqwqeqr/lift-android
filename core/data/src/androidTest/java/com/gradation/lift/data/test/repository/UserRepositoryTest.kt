package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.di.TestDispatcher
import com.gradation.lift.data.fake.datasource.FakeUserRemoteDataSource
import com.gradation.lift.data.repository.DefaultUserRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_MODEL
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class UserRepositoryTest {

    private lateinit var failDataSource: UserRemoteDataSource
    private lateinit var successDataSource: UserRemoteDataSource
    private lateinit var successRepository: UserRepository
    private lateinit var failRepository: UserRepository

    private lateinit var testDispatcher: DispatcherProvider


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeUserRemoteDataSource(TestReturnState.Fail)
        successDataSource = FakeUserRemoteDataSource(TestReturnState.Success)
        testDispatcher = TestDispatcher.testDispatchers()
        successRepository = DefaultUserRepository(successDataSource, testDispatcher)
        failRepository = DefaultUserRepository(failDataSource, testDispatcher)
    }



    @Test
    fun getUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(USER_DETAIL_MODEL)
        ).isEqualTo(
            successRepository.getUserDetail().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getUserDetail().first()
        )
    }

    @Test
    fun createUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.createUserDetail(USER_DETAIL_MODEL).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createUserDetail(USER_DETAIL_MODEL).first()
        )
    }

    @Test
    fun updateUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateUserDetail(USER_DETAIL_MODEL).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateUserDetail(USER_DETAIL_MODEL).first()
        )
    }

    @Test
    fun existUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(DefaultDataGenerator.FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            successRepository.existUserDetail().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.existUserDetail().first()
        )
    }



}