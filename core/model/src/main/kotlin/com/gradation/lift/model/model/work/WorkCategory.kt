package com.gradation.lift.model.model.work


/**
 * [WorkCategory]
 * 운동 카테고리 및 종류를 담아둔 모델
 * @property id  모델의 고유 아이디 번호
 * @property name 해당 운동의 이름
 * @property workPart 해당 운동이 작용하는 부위
 * @property introduce 간단한 설명
 * @property description 해당 운동에 대한 디테일 한 설명
 */
data class WorkCategory(
    val id: Int,
    val name: String,
    val workPart: WorkPart,
    val introduce: String?,
    val description: String?
)