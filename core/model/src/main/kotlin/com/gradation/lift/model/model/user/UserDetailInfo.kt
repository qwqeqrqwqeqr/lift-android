package com.gradation.lift.model.model.user


/**
 * [UserDetailInfo]
 * 사용자의 기본 상세 정보를 담아둔 모델
 * @property gender  성별
 * @property height  키
 * @property weight  몸무게
 * @property unitOfWeight  무게 단위 (현재 미사용)
 */
data class UserDetailInfo(
    val gender: Gender=Gender.Male(),
    val height: Float=0f,
    val weight: Float=0f,
)


