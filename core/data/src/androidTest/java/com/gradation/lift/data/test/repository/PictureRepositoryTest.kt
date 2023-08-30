package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.fake.datasource.FakePictureDataSource
import com.gradation.lift.data.repository.DefaultPictureRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.PictureRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.datasource.picture.PictureDataSource
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

    private lateinit var failDataSource: PictureDataSource
    private lateinit var successDataSource: PictureDataSource
    private lateinit var successRepository: PictureRepository
    private lateinit var failRepository: PictureRepository


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakePictureDataSource(TestReturnState.Fail)
        successDataSource = FakePictureDataSource(TestReturnState.Success)

        successRepository = DefaultPictureRepository(successDataSource)
        failRepository = DefaultPictureRepository(failDataSource)
    }



    @Test
    fun routineSetPicture() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.Picture.routineSetPictureModelList)
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
            DataState.Success(ModelDataGenerator.Picture.userProfilePictureModelList)
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