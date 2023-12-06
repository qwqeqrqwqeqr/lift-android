package com.gradation.lift.feature.update_routine.profile_picture.data.model

/**
 * [RoutineSetCategoryPicture]
 * @property category 카테고리
 * @property pictureList 루틴 프로필 사진으로 선택될 목록
 * @since 2023-08-21 19:07:04
 */
data class RoutineSetCategoryPicture(
    val category: String,
    val pictureList: List<String>
)