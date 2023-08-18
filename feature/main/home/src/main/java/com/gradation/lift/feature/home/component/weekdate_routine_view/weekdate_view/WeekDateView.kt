package com.gradation.lift.feature.home.component.weekdate_routine_view.weekdate_view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import kotlinx.datetime.LocalDate

@Composable
internal fun WeekDateView(
    modifier: Modifier = Modifier,
    today: LocalDate,
    weekDateSelectionList: List<WeekDateSelection>,
    updateSelectedDate: (LocalDate) -> Unit,
) {
    TodayView(modifier, today)
    WeekDateCardView(modifier, weekDateSelectionList, updateSelectedDate)
}


