package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.di.TestDispatcher
import com.gradation.lift.data.fake.datasource.FakePictureRemoteDataSource
import com.gradation.lift.data.repository.DefaultPictureRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.PictureRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.ROUTINE_SET_PICTURE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.USER_PROFILE_PICTURE_MODEL
import com.gradation.lift.network.datasource.picture.PictureRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class PictureRepositoryTest {

    private lateinit var failDataSource: PictureRemoteDataSource
    private lateinit var successDataSource: PictureRemoteDataSource
    private lateinit var successRepository: PictureRepository
    private lateinit var failRepository: PictureRepository
    private lateinit var testDispatcher: DispatcherProvider


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakePictureRemoteDataSource(TestReturnState.Fail)
        successDataSource = FakePictureRemoteDataSource(TestReturnState.Success)
        testDispatcher = TestDispatcher.testDispatchers()

        successRepository = DefaultPictureRepository(successDataSource, testDispatcher)
        failRepository = DefaultPictureRepository(failDataSource, testDispatcher)
    }


    @Test
    fun routineSetPicture() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(ROUTINE_SET_PICTURE_MODEL))
        ).isEqualTo(
            successRepository.getRoutineSetPicture().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getRoutineSetPicture().first()
        )
    }

    @Test
    fun userProfilePicture() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(USER_PROFILE_PICTURE_MODEL))
        ).isEqualTo(
            successRepository.getUserProfilePicture().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getUserProfilePicture().first()
        )
    }

}