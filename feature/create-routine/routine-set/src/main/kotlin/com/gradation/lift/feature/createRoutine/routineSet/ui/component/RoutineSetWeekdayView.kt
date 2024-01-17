package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.modifier.noRippleClickable

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
                Box(
                    modifier = modifier
                        .background(
                            color = if (currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTheme.colorScheme.no13 else LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(size = LiftTheme.space.space6)
                        )
                        .width(LiftTheme.space.space44)
                        .noRippleClickable {
                            currentRoutineSetRoutineState.updateRoutineSetWeekday(getWeekdayEntries().toSet())
                        }
                        .padding(vertical = LiftTheme.space.space10),
                    contentAlignment = Alignment.Center
                ) {
                    LiftText(
                        textStyle = if (currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTextStyle.No5 else LiftTextStyle.No6,
                        text = "전체",
                        color = if (currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center
                    )
                }
            }
            items(getWeekdayEntries()) {
                Box(
                    modifier = modifier
                        .background(
                            color = if (
                                currentRoutineSetRoutine.weekday.contains(
                                    it
                                ) &&
                                currentRoutineSetRoutine.weekday.size != getWeekdayEntries().size
                            ) LiftTheme.colorScheme.no13 else LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(size = LiftTheme.space.space6)
                        )
                        .width(LiftTheme.space.space40)
                        .noRippleClickable {
                            if(currentRoutineSetRoutine.weekday.size == getWeekdayEntries().size){
                                currentRoutineSetRoutineState.updateRoutineSetWeekday(setOf(it))
                            }
                            else {
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
                        .padding(LiftTheme.space.space10),
                    contentAlignment = Alignment.Center
                ) {
                    LiftText(
                        textStyle = if (
                            currentRoutineSetRoutine.weekday.contains(it) &&
                            currentRoutineSetRoutine.weekday.size != getWeekdayEntries().size
                        ) LiftTextStyle.No5 else LiftTextStyle.No6,
                        text = it.getWeekdayName(),
                        color = if (
                            currentRoutineSetRoutine.weekday.contains(it) &&
                            currentRoutineSetRoutine.weekday.size != getWeekdayEntries().size
                        ) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}