package com.gradation.lift.model.history

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


/**
 * [CreateHistory]
 * 운동 기록을 저장하기 위해 생성하는 모델입니다.
 * @property comment 해당 운동에 대한 자체 코멘트
 * @property score 해당 운동에 대한 자체 평가 점수
 * @property workTime 순수 운동 진행 시간
 * @property restTime 순수 운동 휴식 시간
 * @property totalTime 총 운동 시간
 * @property historyTimeStamp 운동이 완료된 시점
 * @property historyRoutine 해당 기록에 대한 운동들의 리스트
 **/
data class CreateHistory(
    val comment : String?,
    val score :Int,
    val workTime : LocalTime,
    val restTime : LocalTime,
    val totalTime : LocalTime,
    val historyTimeStamp : LocalDateTime,
    val historyRoutine : List<CreateHistoryRoutine>
)
