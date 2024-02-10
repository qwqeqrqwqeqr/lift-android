package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Composable
internal fun RoutineSetWeekdayView(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
        ) {
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "루틴 요일",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "어떤 요일에 운동 하실건가요?",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            item {
                LiftDefaultChip(
                    modifier = modifier,
                    text = "전체",
                    isSelected = (currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size),
                    onClick = {
                        currentRoutineSetRoutineState.updateRoutineSetWeekday(
                            getWeekdayEntries().toSet()
                        )
                    }
                )
            }
            items(getWeekdayEntries()) {
                LiftDefaultChip(
                    modifier = modifier,
                    text = it.getWeekdayName(),
                    isSelected = (currentRoutineSetRoutine.weekday.contains(it) &&
                            currentRoutineSetRoutine.weekday.size != getWeekdayEntries().size),
                    onClick = {
                        if (currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size) {
                            currentRoutineSetRoutineState.updateRoutineSetWeekday(setOf(it))
                        } else {
                            if (currentRoutineSetRoutine.weekday.contains(it)) {
                                if (currentRoutineSetRoutine.weekday.size != 1) {
                                    currentRoutineSetRoutineState.updateRoutineSetWeekday(
                                        currentRoutineSetRoutine.weekday.minus(it)
                                    )
                                }
                            } else {
                                currentRoutineSetRoutineState.updateRoutineSetWeekday(
                                    currentRoutineSetRoutine.weekday.plus(it)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}