package com.gradation.lift.model.model.date


/**
 * [WeekDateMonth]
 * 월에 대한 날짜 정보
 * @property month 해당 월 정보
 * @property weekDateList 주차와 해당 주차에 속해있는 날짜 key: 주차 value: 해당 주차에 속해있는 [WeekDate]
 * @since 2023-08-23 20:28:09
 */
data class WeekDateMonth(
    val month: Int = 0,
    val weekDateList: Map<Int, List<WeekDate>> = emptyMap(),
)



