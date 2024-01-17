package com.gradation.lift.feature.workReady.routineSelection.ui.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.selector.LiftDefaultSelector
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.SortFilterState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 요일 필터 바텀 시트
 * @since 2023-12-03 22:47:27
 */
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
                .padding(LiftTheme.space.space20)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    LiftMultiStyleText(
                        defaultColor = LiftTheme.colorScheme.no9,
                        defaultTextStyle = LiftTextStyle.No2,
                        textAlign = TextAlign.Start,
                        textWithStyleList = listOf(
                            TextWithStyle("어떻게 "),
                            TextWithStyle("분류", color = LiftTheme.colorScheme.no4),
                            TextWithStyle("할까요?"),
                        )
                    )
                }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space10)
                        .noRippleClickable {
                            routineListScreenState.updateWeekdayFilterTypeBottomSheetView(false)
                        },
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LiftDefaultSelector(
                        modifier.weight(1f),
                        "전체",
                        weekdayFilterType.isCheckedAllWeekday()
                    ) { sortFilterState.updateAllWeekdayFilter() }

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
    weekday: Weekday,
) {
    LiftDefaultSelector(
        modifier,
        "${weekday.getWeekdayName()}요일",
        (weekdayFilterType.contains(weekday) && !weekdayFilterType.isCheckedAllWeekday())
    ) {
        if (weekdayFilterType.isCheckedAllWeekday()) {
            sortFilterState.updateWeekdayFilter(setOf(weekday))
        } else {
            if (weekdayFilterType.contains(weekday)) {
                if (!weekdayFilterType.isCheckedOne()) {
                    sortFilterState.updateWeekdayFilter(
                        weekdayFilterType.weekdaySet.minus(weekday)
                    )
                }else{
                    sortFilterState.updateAllWeekdayFilter()
                }
            } else {
                sortFilterState.updateWeekdayFilter(
                    weekdayFilterType.weekdaySet.plus(weekday)
                )
            }
        }
    }
}