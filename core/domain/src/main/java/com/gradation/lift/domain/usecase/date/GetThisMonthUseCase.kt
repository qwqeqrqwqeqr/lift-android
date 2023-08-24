package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import javax.inject.Inject
import java.time.LocalDate as JavaLocalDate
import kotlinx.datetime.LocalDate as KotlinLocalDate

/**
 * [GetThisMonthUseCase]
 * 해당 일이 속해있는 달에 모든 날짜 정보를 가져옴
 */
class GetThisMonthUseCase @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(date: KotlinLocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<KotlinLocalDate> {
        return JavaLocalDate.parse(date.toString())!!.let { localDate ->
            (1..localDate.lengthOfMonth()).map {
                KotlinLocalDate.parse(localDate.withDayOfMonth(it)!!.toString())
            }
        }
    }
}
