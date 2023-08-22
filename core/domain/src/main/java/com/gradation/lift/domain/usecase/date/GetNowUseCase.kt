package com.gradation.lift.domain.usecase.date

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import java.time.LocalDateTime
import javax.inject.Inject

class GetNowUseCase  @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): kotlinx.datetime.LocalDateTime {
        return LocalDateTime.now().toKotlinLocalDateTime()
    }
}
