package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakePictureDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.ROUTINE_SET_PICTURE_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.USER_PROFILE_PICTURE_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class PictureDataSourceTest {



    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: PictureDataSource


    @Test
    fun routineSetPictureDataSource() = runTest {
        dataSource =
            FakePictureDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(listOf(ROUTINE_SET_PICTURE_MODEL))
        ).isEqualTo(
            dataSource.getRoutineSetPicture().first()
        )


        dataSource =
            FakePictureDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getRoutineSetPicture().first()
        )
    }

    @Test
    fun userProfilePictureDataSource() = runTest {
        dataSource =
            FakePictureDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(listOf(USER_PROFILE_PICTURE_MODEL))
        ).isEqualTo(
            dataSource.getUserProfilePicture().first()
        )

        dataSource =
            FakePictureDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getUserProfilePicture().first()
        )
    }
}