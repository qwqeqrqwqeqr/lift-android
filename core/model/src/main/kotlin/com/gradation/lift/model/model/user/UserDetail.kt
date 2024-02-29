package com.gradation.lift.model.model.user


/**
 * [UserDetail]
 * 사용자의 상세 정보를 담아둔 모델
 * @property name  이름
 * @property gender  성별
 * @property height  키
 * @property weight  몸무게
 * @property profilePicture  프로필 사진
 * @property unitOfWeight  무게 단위 (현재 미사용)
 */
data class UserDetail(
    val name: String="",
    val gender: Gender=Gender.Male(),
    val height: Float=0f,
    val weight: Float=0f,
    val profilePicture: String="",
    val unitOfWeight: UnitOfWeight = UnitOfWeight.Kg(),
)


