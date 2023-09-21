package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Badge.createUserBadgeModel
import com.gradation.lift.network.data.TestDtoDataGenerator.Badge.createUserBadgeDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateUserBadgeMapperTest {

    @Test
    fun createUserBadgeMapper() = runTest {

        with(createUserBadgeModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(createUserBadgeDto.id)
            Truth.assertThat(this.badgeTimeStamp).isEqualTo(createUserBadgeDto.badgeTimeStamp)
        }
    }
}