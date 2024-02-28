package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.CREATE_USER_BADGE_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.CreateUserBadge.CREATE_USER_BADGE_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.test.runTest
import org.junit.Test


class CreateUserBadgeMapperTest {

    @Test
    fun createUserBadgeMapper() = runTest {
        with(CREATE_USER_BADGE_MODEL.toDto()) {
            Truth.assertThat(id).isEqualTo(CREATE_USER_BADGE_REQUEST_DTO.createUserBadgeDto.id)
            Truth.assertThat(badgeTimeStamp)
                .isEqualTo(CREATE_USER_BADGE_REQUEST_DTO.createUserBadgeDto.badgeTimeStamp)
        }
    }
}