package com.gradation.lift.feature.work.work.data.model

/**
 * [WorkSetSelection]
 * 운동 개별 세트에 대한 횟수 무게 및 완료여부를 표시하는 모델
 * @property set  운동 세트 (해당 운동의 아이디와 현재 세트를 표기)
 * @property weight  무게
 * @property repetition  횟수
 * @property selected  운동을 완료했는지에 대한 여부
 * @since 2023-08-22 16:24:16
 */
data class WorkSetSelection(
    val set: Pair<Int, Int>,
    val weight: Float,
    val repetition: Int,
    var selected: Boolean = false
)