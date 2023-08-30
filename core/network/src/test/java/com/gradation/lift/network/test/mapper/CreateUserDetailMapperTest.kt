package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.User.createUserDetailModel
import com.gradation.lift.network.data.TestDtoDataGenerator.User.createUserDetailRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateUserDetailMapperTest {

    @Test
    fun createUserDetailMapper() = runTest {

        with(createUserDetailModel.toDto()) {
            Truth.assertThat(this.gender).isEqualTo(createUserDetailRequestDto.userDetailDto.gender)
            Truth.assertThat(this.height).isEqualTo(createUserDetailRequestDto.userDetailDto.height)
            Truth.assertThat(this.weight).isEqualTo(createUserDetailRequestDto.userDetailDto.weight)
            Truth.assertThat(this.name).isEqualTo(createUserDetailRequestDto.userDetailDto.name)
            Truth.assertThat(this.profilePicture)
                .isEqualTo(createUserDetailRequestDto.userDetailDto.profilePicture)
            Truth.assertThat(this.unitOfWeight)
                .isEqualTo(createUserDetailRequestDto.userDetailDto.unitOfWeight)
        }
    }
}