package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
internal fun RoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateRoutineSetToUpdateWorkSetInCreateRoutineGraph: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no17)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
    ) {
        routineSetRoutine.routine.forEachIndexed { index, routine ->
            LiftDefaultContainer(
                modifier = modifier.noRippleClickable {
                    navigateRoutineSetToUpdateWorkSetInCreateRoutineGraph(index)
                },
                shape = RoundedCornerShape(size = LiftTheme.space.space12),
                horizontalPadding = LiftTheme.space.space16,
                verticalPadding = LiftTheme.space.space16
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = routine.workCategoryName,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )
                        Icon(
                            modifier = modifier
                                .size(LiftTheme.space.space12)
                                .noRippleClickable {
                                    currentRoutineSetRoutineState.removeRoutine(
                                        routine
                                    )
                                },
                            painter = painterResource(id = LiftIcon.Close),
                            contentDescription = "remove",
                            tint = LiftTheme.colorScheme.no3,
                        )
                    }

                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        Row(
                            modifier = modifier.padding(horizontal = LiftTheme.space.space16),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
                        ) {
                            LiftText(
                                modifier = modifier.weight(1f),
                                textStyle = LiftTextStyle.No3,
                                text = "Set",
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                            LiftText(
                                modifier = modifier.weight(1f),
                                textStyle = LiftTextStyle.No3,
                                text = "Kg",
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                            LiftText(
                                modifier = modifier.weight(1f),
                                textStyle = LiftTextStyle.No3,
                                text = "Reps",
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                        }
                        routine.workSetList.forEachIndexed { index, workSet ->
                            LiftPrimaryContainer(
                                modifier = modifier,
                                horizontalPadding = LiftTheme.space.space16,
                                verticalPadding = LiftTheme.space.space8,
                                shape = RoundedCornerShape(size = LiftTheme.space.space8)
                            ) {
                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No3,
                                        text = "${index + 1}",
                                        color = LiftTheme.colorScheme.no2,
                                        textAlign = TextAlign.Center
                                    )
                                    LiftEmptyContainer(
                                        modifier = modifier.weight(1f),
                                        verticalPadding = LiftTheme.space.space6,
                                        shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                    ) {
                                        LiftText(
                                            modifier = modifier.align(Alignment.Center),
                                            textStyle = LiftTextStyle.No3,
                                            text = workSet.weight.toText(),
                                            color = LiftTheme.colorScheme.no10,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    LiftEmptyContainer(
                                        modifier = modifier.weight(1f),
                                        verticalPadding = LiftTheme.space.space6,
                                        shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                    ) {
                                        LiftText(
                                            modifier = modifier.align(Alignment.Center),
                                            textStyle = LiftTextStyle.No3,
                                            text = "${workSet.repetition}",
                                            color = LiftTheme.colorScheme.no10,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}