package com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListScreenState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.model.model.date.Weekday

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WeekdayFilterBottomSheet(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    sortFilterState: SortFilterState,
    weekdayFilterType: WeekdayFilterType
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { routineListScreenState.updateWeekdayFilterTypeBottomSheetView(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.search_3d),
                        contentDescription = "filterSearch"
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("어떻게 ")
                            withStyle(
                                style = SpanStyle(color = LiftTheme.colorScheme.no4),
                            ) {
                                append("분류")
                            }
                            append("할까요?")
                        },
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9
                    )
                }
                IconButton(
                    modifier = modifier.size(16.dp),
                    onClick = { routineListScreenState.updateWeekdayFilterTypeBottomSheetView(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "",
                        tint = LiftTheme.colorScheme.no9,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LiftButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = {
                            sortFilterState.updateAllWeekdayFilter()
                        },
                        containerColor = if (weekdayFilterType.isCheckedAllWeekday()) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                        contentColor = if (weekdayFilterType.isCheckedAllWeekday()) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9
                    ) {
                        Text(
                            text = "전체",
                            style = if (weekdayFilterType.isCheckedAllWeekday()) LiftTheme.typography.no3 else LiftTheme.typography.no4,
                        )
                    }
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Monday()
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Tuesday()
                    )
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Wednesday()
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Thursday()
                    )
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Friday()
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Saturday()
                    )
                    WeekdayButton(
                        modifier.weight(1f),
                        weekdayFilterType,
                        sortFilterState,
                        Weekday.Sunday()
                    )
                }
            }
        }
    }
}


@Composable
internal fun WeekdayButton(
    modifier: Modifier = Modifier,
    weekdayFilterType: WeekdayFilterType,
    sortFilterState: SortFilterState,
    weekday: Weekday
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            if (weekdayFilterType.isCheckedAllWeekday()) {
                sortFilterState.updateWeekdayFilter(setOf(weekday))
            } else {
                if (weekdayFilterType.contains(weekday)) {
                    if (!weekdayFilterType.isCheckedOne()) {
                        sortFilterState.updateWeekdayFilter(
                            weekdayFilterType.weekdaySet.minus(weekday)
                        )
                    }
                } else {
                    sortFilterState.updateWeekdayFilter(
                        weekdayFilterType.weekdaySet.plus(weekday)
                    )
                }
            }
        },
        containerColor = if (weekdayFilterType.contains(weekday) && !weekdayFilterType.isCheckedAllWeekday()) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no1,
        contentColor = if (weekdayFilterType.contains(weekday) && !weekdayFilterType.isCheckedAllWeekday()) LiftTheme.colorScheme.no5
        else LiftTheme.colorScheme.no9
    ) {
        Text(
            text = "${weekday.getWeekdayName()}요일",
            style = if (weekdayFilterType.contains(weekday) && !weekdayFilterType.isCheckedAllWeekday()) LiftTheme.typography.no3
            else LiftTheme.typography.no4
        )
    }
}