package com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.ui.mapper.toText

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
    routineIndex: Int?,
    workSetState: WorkSetState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
) {

    LiftDefaultBottomBar(
        modifier = modifier

    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                Row(
                    modifier = modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Icon(
                            modifier = modifier.size(LiftTheme.space.space24),
                            imageVector = ImageVector.vectorResource(id = LiftIcon.Weight),
                            contentDescription = "Weight",
                            tint = Color.Unspecified
                        )
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = "총 무게",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                    }
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space1),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedContent(
                            targetState = workSetState.createWorkSetLists
                                .mapNotNull {
                                    if (it.repetition.toIntOrNull() != null && it.weight.toFloatOrNull() != null)
                                        it.repetition.toInt().toFloat() * it.weight.toFloat()
                                    else null
                                }.sum(),
                            transitionSpec = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(durationMillis = 300)
                                ) togetherWith ExitTransition.None
                            }, label = ""
                        ) { value ->
                            LiftText(
                                textStyle = LiftTextStyle.No2,
                                text = value.toText(),
                                color = LiftTheme.colorScheme.no4,
                                textAlign = TextAlign.Start
                            )
                        }
                        LiftText(
                            textStyle = LiftTextStyle.No2,
                            text = "kg",
                            color = LiftTheme.colorScheme.no4,
                            textAlign = TextAlign.Start
                        )
                    }


                }
                Row(
                    modifier = modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = modifier.size(LiftTheme.space.space24),
                            imageVector = ImageVector.vectorResource(id = LiftIcon.Set),
                            contentDescription = "Set",
                            tint = Color.Unspecified

                        )
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = "총 세트",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                    }
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space1),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedContent(
                            targetState = workSetState.createWorkSetLists.size,
                            transitionSpec = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(durationMillis = 300)
                                ) togetherWith ExitTransition.None
                            }, label = ""
                        ) { value ->
                            LiftText(
                                textStyle = LiftTextStyle.No2,
                                text = "$value",
                                color = LiftTheme.colorScheme.no4,
                                textAlign = TextAlign.Start
                            )
                        }
                        LiftText(
                            textStyle = LiftTextStyle.No2,
                            text = "set",
                            color = LiftTheme.colorScheme.no4,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            LiftSolidButton(
                modifier = modifier,
                enabled = workSetState.createWorkSetLists.isNotEmpty()
                        && workSetState.createWorkSetLists.none {
                    it.weight.isEmpty() || !decimalNumberValidator(it.weight)
                }
                        && workSetState.createWorkSetLists.none {
                    it.repetition.isEmpty() || it.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                        it.repetition
                    )
                },
                text = "등록하기",
                onClick = {
                    currentRoutineSetRoutineState.updateRoutine(
                        routineIndex!!,
                        Routine(
                            id = null,
                            routineSetId = 0,
                            workCategoryId = workCategory.id,
                            workCategoryName = workCategory.name,
                            workPart = workCategory.workPart,
                            workSetList = workSetState.createWorkSetLists.mapIndexed { index, workSet ->
                                WorkSet(
                                    workSetId = index,
                                    weight = workSet.weight.toFloat(),
                                    repetition = workSet.repetition.toInt()
                                )
                            }
                        )
                    )
                    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph()
                }
            )
        }
    }
}
