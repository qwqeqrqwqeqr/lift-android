package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.DEFAULT_SIGN_UP_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.GOOGLE_SIGN_UP_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.KAKAO_SIGN_UP_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.NAVER_SIGN_UP_INFO_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpDefault.SIGN_UP_DEFAULT_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpGoogle.SIGN_UP_GOOGLE_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpKakao.SIGN_UP_KAKAO_REQUEST_DTO
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.SignUpNaver.SIGN_UP_NAVER_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SignUpMapperTest {

    @Test
    fun signUpDefaultMapper() {

        with(DEFAULT_SIGN_UP_INFO_MODEL.toDto()) {
            Truth.assertThat(this.email).isEqualTo(SIGN_UP_DEFAULT_REQUEST_DTO.email)
            Truth.assertThat(this.password).isEqualTo(SIGN_UP_DEFAULT_REQUEST_DTO.password)
        }
    }

    @Test
    fun signUpKakaoMapper() {

        with(KAKAO_SIGN_UP_INFO_MODEL.toDto()) {
            Truth.assertThat(this.email).isEqualTo(SIGN_UP_KAKAO_REQUEST_DTO.email)
            Truth.assertThat(this.id).isEqualTo(SIGN_UP_KAKAO_REQUEST_DTO.id)
        }
    }

    @Test
    fun signUpNaverMapper() {

        with(NAVER_SIGN_UP_INFO_MODEL.toDto()) {
            Truth.assertThat(this.email).isEqualTo(SIGN_UP_NAVER_REQUEST_DTO.email)
            Truth.assertThat(this.id).isEqualTo(SIGN_UP_NAVER_REQUEST_DTO.id)
        }
    }

    @Test
    fun signUpGoogleMapper() {

        with(GOOGLE_SIGN_UP_INFO_MODEL.toDto()) {
            Truth.assertThat(this.email).isEqualTo(SIGN_UP_GOOGLE_REQUEST_DTO.email)
            Truth.assertThat(this.id).isEqualTo(SIGN_UP_GOOGLE_REQUEST_DTO.id)
        }
    }


}