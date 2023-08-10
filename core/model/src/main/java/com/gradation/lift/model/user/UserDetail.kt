package com.gradation.lift.model.user

import com.gradation.lift.model.common.UnitOfWeight


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
    val name: String,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val profilePicture: String,
    val unitOfWeight: UnitOfWeight = UnitOfWeight.Kg(),
)


