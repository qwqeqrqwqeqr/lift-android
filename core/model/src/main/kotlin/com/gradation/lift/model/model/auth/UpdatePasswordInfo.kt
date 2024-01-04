package com.gradation.lift.model.model.auth


/**
 * [UpdatePasswordInfo]
 * 비밀번호 업데이트를 위한 정보를 담고 있는 모델
 * @param email  회원의 이메일
 * @param password 회원의 비밀번호
 */
data class UpdatePasswordInfo(
    val email :String,
    val password : String
)