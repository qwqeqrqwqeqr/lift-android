package com.gradation.lift.feature.work.complete.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.date.GetNowUseCase
import com.gradation.lift.domain.usecase.history.CreateHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject


@HiltViewModel
internal class CompleteViewModel @Inject constructor(
    getNowUseCase: GetNowUseCase
): ViewModel(){

    val currentTime: LocalDateTime = getNowUseCase()
}