package com.gradation.lift.model.model.history

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


/**
 * [CreateHistoryRoutine]
 * 운동 기록에 대한 모델입니다.
 * 여러 개의 [HistoryRoutine]을 보유하고 있습니다.
 * @property historyId 해당 모델의 고유 아이디
 * @property comment 해당 운동에 대한 자체 코멘트
 * @property score 해당 운동에 대한 자체 평가 점수
 * @property workTime 순수 운동 휴식 시간
 * @property restTime 순수 운동 휴식 시간
 * @property totalTime 총 운동 시간
 * @property historyTimeStamp 운동이 완료된 시점
 * @property historyRoutine 해당 기록에 대한 운동들의 리스트
 **/
data class History(
    val historyId: Int = 0,
    val comment: String? = null,
    val score: Int? = null,
    val progress: Int = 0,
    val workTime: LocalTime = LocalTime(0, 0, 0),
    val restTime: LocalTime = LocalTime(0, 0, 0),
    val totalTime: LocalTime = LocalTime(0, 0, 0),
    val historyTimeStamp: LocalDateTime = LocalDateTime(2022, 2, 22, 0, 0, 0),
    val historyRoutine: List<HistoryRoutine> = emptyList(),
)
