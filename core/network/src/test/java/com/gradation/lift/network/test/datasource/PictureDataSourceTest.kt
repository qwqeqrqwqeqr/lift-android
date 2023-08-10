package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.PictureDataSource
import com.gradation.lift.network.fake.FakePictureDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.routineSetPictureModelList
import com.gradation.lift.model.utils.ModelDataGenerator.Picture.userProfilePictureModelList
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
    fun testRoutineSetPictureDataSource() = runTest {
        dataSource = FakePictureDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(routineSetPictureModelList)
        ).isEqualTo(
            dataSource.getRoutineSetPicture().first()
        )
    }

    @Test
    fun testUserProfilePictureDataSource() = runTest {
        dataSource = FakePictureDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(userProfilePictureModelList)
        ).isEqualTo(
            dataSource.getUserProfilePicture().first()
        )
    }
}