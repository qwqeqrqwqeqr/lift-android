package com.gradation.lift.model.model.auth

/**
 * [NaverSignInInfo]
 * 네이버 로그인을 위한 정보를 담고 있는 모델
 * @param id  회원의 아이디
 * @param email  회원의 이메일
 * @since 2023-08-16 16:01:26
 */
data class NaverSignInInfo(
    val id :String,
    val email :String,
)