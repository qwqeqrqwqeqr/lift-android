package com.gradation.lift.feature.home.home.data.state

import kotlinx.datetime.LocalDate

/**
 * [WorkStampState]
 * @param continuousWorkCount 연속 운동 일 수
 * @param today 현재 날짜,
 * @param workByDay 일 별 운동 여부 (한 주를 단위로 리스트 구성)
 * @param countOfWorkInMonth 한달 운동 횟수
 * @param endOfDay 해당 월의 마지막 날짜
 * @since 2024-02-13 00:38:45
 */
data class WorkStampState(
    val continuousWorkCount: Int,
    val today: LocalDate,
    val workByDay: List<Pair<Boolean, LocalDate>>,
    val countOfWorkInMonth: Int,
    val endOfDay: Int,

    )
