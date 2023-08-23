package com.gradation.lift.feature.history.analytics.data.state

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateOfThisMonthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class WorkCategoryAnalyticsState(){}