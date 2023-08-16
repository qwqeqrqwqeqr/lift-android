package com.gradation.lift.model.model.auth

/**
 * [KakaoSignInInfo]
 * 카카오 로그인을 위한 정보를 담고 있는 모델
 * @param id  회원의 아이디
 * @param email  회원의 계정 이메일
 * @since 2023-08-16 16:01:14
 */
data class KakaoSignInInfo(
    val id :String,
    val email :String,
)