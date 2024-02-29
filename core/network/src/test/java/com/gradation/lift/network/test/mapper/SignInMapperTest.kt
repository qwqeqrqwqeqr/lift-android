package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.DEFAULT_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.GOOGLE_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.KAKAO_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.NAVER_SIGN_IN_INFO_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInDefault.SIGN_IN_DEFAULT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInGoogle.SIGN_IN_GOOGLE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInKakao.SIGN_IN_KAKAO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignInNaver.SIGN_IN_NAVER_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import org.junit.Test


class SignInMapperTest {

    @Test
    fun signInDefaultMapper() {

        with(DEFAULT_SIGN_IN_INFO_MODEL.toDto()) {
            Truth.assertThat(this.id).isEqualTo(SIGN_IN_DEFAULT_REQUEST_DTO.id)
            Truth.assertThat(this.password).isEqualTo(SIGN_IN_DEFAULT_REQUEST_DTO.password)
        }
    }

    @Test
    fun signInKakaoMapper() {

        with(KAKAO_SIGN_IN_INFO_MODEL.toDto()) {
            Truth.assertThat(this.id).isEqualTo(SIGN_IN_KAKAO_REQUEST_DTO.id)
            Truth.assertThat(this.email).isEqualTo(SIGN_IN_KAKAO_REQUEST_DTO.email)
        }
    }

    @Test
    fun signInNaverMapper() {

        with(NAVER_SIGN_IN_INFO_MODEL.toDto()) {
            Truth.assertThat(this.id).isEqualTo(SIGN_IN_NAVER_REQUEST_DTO.id)
            Truth.assertThat(this.email).isEqualTo(SIGN_IN_NAVER_REQUEST_DTO.email)
        }
    }

    @Test
    fun signInGoogleMapper() {
        with(GOOGLE_SIGN_IN_INFO_MODEL.toDto()) {
            Truth.assertThat(this.id).isEqualTo(SIGN_IN_GOOGLE_REQUEST_DTO.id)
            Truth.assertThat(this.email).isEqualTo(SIGN_IN_GOOGLE_REQUEST_DTO.email)
        }
    }
}