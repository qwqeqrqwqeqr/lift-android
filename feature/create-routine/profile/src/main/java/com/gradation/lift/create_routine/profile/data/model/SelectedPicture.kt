package com.gradation.lift.create_routine.profile.data.model

/**
 * [SelectedPicture]
 * @property url 사진의 url 주소
 * @property selected 해당 사진이 선택되었는지에 대한 여부
 * @since 2023-08-21 19:06:56
 */
data class SelectedPicture(
    val url: String,
    val selected: Boolean = false
)