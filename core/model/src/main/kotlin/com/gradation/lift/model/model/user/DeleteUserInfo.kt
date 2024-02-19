package com.gradation.lift.model.model.user


/**
 * [DeleteUserInfo]
 * 사용자 삭제 정보를 담아내는 모델
 * @property reason 삭제 이유
 */
data class DeleteUserInfo(
    val reason: String = "",
)


