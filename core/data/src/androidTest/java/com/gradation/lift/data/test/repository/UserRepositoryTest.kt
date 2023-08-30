package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.fake.datasource.FakeUserDataSource
import com.gradation.lift.data.repository.DefaultUserRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.datasource.user.UserDataSource
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

    private lateinit var failDataSource: UserDataSource
    private lateinit var successDataSource: UserDataSource
    private lateinit var successRepository: UserRepository
    private lateinit var failRepository: UserRepository


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeUserDataSource(TestReturnState.Fail)
        successDataSource = FakeUserDataSource(TestReturnState.Success)

        successRepository = DefaultUserRepository(successDataSource)
        failRepository = DefaultUserRepository(failDataSource)
    }



    @Test
    fun getUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.User.userDetailModel)
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
            successRepository.createUserDetail(ModelDataGenerator.User.createUserDetailModel).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createUserDetail(ModelDataGenerator.User.createUserDetailModel).first()
        )
    }

    @Test
    fun updateUserDetailDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.updateUserDetail(ModelDataGenerator.User.userDetailModel).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.updateUserDetail(ModelDataGenerator.User.userDetailModel).first()
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