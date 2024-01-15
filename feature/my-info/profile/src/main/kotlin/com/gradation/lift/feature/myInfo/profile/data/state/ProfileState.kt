package com.gradation.lift.feature.myInfo.profile.data.state

import com.gradation.lift.model.model.user.Gender


/**
 * [ProfileState]
 * 프로필 화면에서 사용될 데이터들을 집합한 상태
 * @since 2024-01-12 15:32:12
 */
data class ProfileState(
    val name: String,
    val profilePicture: String,
    val height: Float,
    val weight: Float,
    val gender: Gender,
)
