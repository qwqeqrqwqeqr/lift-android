package com.gradation.lift.model.model.work

import kotlinx.datetime.LocalTime
import java.util.UUID


/**
 * [Work]
 * 현재 운동 중일 때의 정보 들을 담아둔 모델
 * @property id  모델의 고유 아이디 번호
 * @property workTime 현재 까지 운동한 시간
 * @property restTime 현재 까지 휴식한 시간
 * @property totalTime 종합 운동 시간 ([totalTime] + [workTime])
 * @property routine 현재 진행중인 운동들을 모아둔 리스트
 * @property usedRoutineSetIdList 사용된 루틴 세트 아이디
 */
data class Work(
    val id: UUID,
    val workTime : LocalTime= LocalTime(0,0,0),
    val restTime: LocalTime=LocalTime(0,0,0),
    val totalTime: LocalTime=LocalTime(0,0,0),
    val routine: List<WorkRoutine>,
    val usedRoutineSetIdList : List<Int> = emptyList()
)