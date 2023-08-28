package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * [GetNowUseCase]
 * 현재 날짜 및 시간을 구하는 유즈케이스
 * @since 2023-08-28 20:22:31
 */
class GetNowUseCase  @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): kotlinx.datetime.LocalDateTime {
        return LocalDateTime.now().toKotlinLocalDateTime()
    }
}
