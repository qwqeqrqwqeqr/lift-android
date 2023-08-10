package com.gradation.lift.model.auth


/**
 * [DefaultSignUpInfo]
 * 회원 가입을 위한 정보를 담고 있는 모델
 * @param id  회원의 아이디 (이메일 형태를 가짐)
 * @param password 회원의 비밀번호
 */
data class DefaultSignUpInfo(
    val id :String,
    val password : String
)