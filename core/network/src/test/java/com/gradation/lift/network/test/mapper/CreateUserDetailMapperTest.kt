package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.User.CreateUserDetail.CREATE_USER_DETAIL_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateUserDetailMapperTest {

    @Test
    fun createUserDetailMapper() = runTest {

        with(USER_DETAIL_MODEL.toDto()) {
            Truth.assertThat(gender).isEqualTo(CREATE_USER_DETAIL_REQUEST_DTO.userDetailDto.gender)
            Truth.assertThat(height).isEqualTo(CREATE_USER_DETAIL_REQUEST_DTO.userDetailDto.height)
            Truth.assertThat(weight).isEqualTo(CREATE_USER_DETAIL_REQUEST_DTO.userDetailDto.weight)
            Truth.assertThat(name).isEqualTo(CREATE_USER_DETAIL_REQUEST_DTO.userDetailDto.name)
            Truth.assertThat(profilePicture)
                .isEqualTo(CREATE_USER_DETAIL_REQUEST_DTO.userDetailDto.profilePicture)
        }
    }
}