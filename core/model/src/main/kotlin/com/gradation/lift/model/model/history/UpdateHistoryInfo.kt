package com.gradation.lift.model.model.history


/**
 * [UpdateHistoryInfo]
 * 운동 기록 정보를 업데이트 하기 위한 모델
 * @property historyId 기록 아이디
 * @property comment 수정할 점수
 * @property score 수정할 메모
 **/
data class UpdateHistoryInfo(
    val historyId:Int,
    val score:Int,
    val comment:String
)
