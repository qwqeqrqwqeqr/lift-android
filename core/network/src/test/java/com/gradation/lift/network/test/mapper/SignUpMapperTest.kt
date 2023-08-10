package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.data.TestDtoDataGenerator
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SignUpMapperTest {

    @Test
    fun testSignUpMapper() = runTest {

        with(ModelDataGenerator.Auth.signUpInfoModel.toDto()) {
            Truth.assertThat(this.email).isEqualTo(TestDtoDataGenerator.Auth.signUpDefaultRequestDto.email)
            Truth.assertThat(this.password).isEqualTo(TestDtoDataGenerator.Auth.signUpDefaultRequestDto.password)
        }
    }
}