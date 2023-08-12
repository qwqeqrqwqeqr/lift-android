package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.signInInfoModel
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultRequestDto
import com.gradation.lift.network.mapper.toDto
import org.junit.Test


class SignInMapperTest {

    @Test
    fun testSignInMapper() {

        with(signInInfoModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(signInDefaultRequestDto.id)
            Truth.assertThat(this.password).isEqualTo(signInDefaultRequestDto.password)
        }
    }
}