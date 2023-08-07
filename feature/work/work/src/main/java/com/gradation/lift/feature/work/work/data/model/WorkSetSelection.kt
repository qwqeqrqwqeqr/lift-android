package com.gradation.lift.feature.work.work.data.model

/**
 * [WorkSetSelection]
 * 운동 개별 세트에 대한 횟수 무게 및 완료여부를 표시하는 클래스
 * @param set : 운동 세트 (해당 운동의 아이디와 현재 세트를 표기)
 * @param weight : 무게
 * @param repetition : 횟수
 * @param selected : 운동을 완료했는지에 대한 여부
 */
data class WorkSetSelection(
    val set: Pair<Int, Int>,
    val weight: Float,
    val repetition: Int,
    var selected: Boolean = false
)