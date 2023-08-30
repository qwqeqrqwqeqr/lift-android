package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.defaultSignInInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.kakaoSignInInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.naverSignInInfoModel
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInKakaoRequestDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInNaverRequestDto
import com.gradation.lift.network.mapper.toDto
import org.junit.Test


class SignInMapperTest {

    @Test
    fun signInDefaultMapper() {

        with(defaultSignInInfoModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(signInDefaultRequestDto.id)
            Truth.assertThat(this.password).isEqualTo(signInDefaultRequestDto.password)
        }
    }

    @Test
    fun signInKakaoMapper() {

        with(kakaoSignInInfoModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(signInKakaoRequestDto.id)
            Truth.assertThat(this.email).isEqualTo(signInKakaoRequestDto.email)
        }
    }

    @Test
    fun signInNaverMapper() {

        with(naverSignInInfoModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(signInNaverRequestDto.id)
            Truth.assertThat(this.email).isEqualTo(signInNaverRequestDto.email)
        }
    }
}