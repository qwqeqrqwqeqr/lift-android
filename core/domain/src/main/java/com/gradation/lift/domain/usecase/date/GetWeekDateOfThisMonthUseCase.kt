package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.model.model.date.WeekDateMonth
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.toWeekday
import kotlinx.datetime.Clock
import java.time.LocalDate as JavaLocalDate
import kotlinx.datetime.LocalDate as KotlinLocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

/**
 * [GetWeekDateOfCurrentMonthUseCase]
 * 한달의 주차 요일 날짜 정보를 구하는 UseCase
 * 입력된 날짜 기준 한달 데이터를 가져옴,
 * 파라미터를 입력하지 않을 경우 현재날짜 기준으로 판단
 * [WeekDateMonth] 반환
 */
class GetWeekDateOfCurrentMonthUseCase @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(date: KotlinLocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<WeekDateMonth> {
        with(JavaLocalDate.parse(date.toString())!!.let { localDate ->
            (1..localDate.lengthOfMonth()).map {
                KotlinLocalDate.parse(localDate.withDayOfMonth(it)!!.toString())
            }
        }) {
            var weekCount: Int = if (first().toWeekday() == Weekday.Sunday()) 0 else 1
            return map {
                if (it.toWeekday() == Weekday.Sunday()) {
                    weekCount += 1
                }
                WeekDateMonth(
                    month = first().monthNumber,
                    week = weekCount,
                    weekday = it.toWeekday(),
                    date = it
                )
            }
        }
    }


}