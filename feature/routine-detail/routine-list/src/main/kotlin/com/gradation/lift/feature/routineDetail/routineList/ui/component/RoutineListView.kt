package com.gradation.lift.feature.routineDetail.routineList.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.label.AllRoutineLabel
import com.gradation.lift.designsystem.component.label.FridayRoutineLabel
import com.gradation.lift.designsystem.component.label.MondayRoutineLabel
import com.gradation.lift.designsystem.component.label.RecentRoutineLabel
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.label.SaturdayRoutineLabel
import com.gradation.lift.designsystem.component.label.SundayRoutineLabel
import com.gradation.lift.designsystem.component.label.ThursdayRoutineLabel
import com.gradation.lift.designsystem.component.label.TuesdayRoutineLabel
import com.gradation.lift.designsystem.component.label.WednesdayRoutineLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routineDetail.routineList.data.model.RecentUsedRoutineSetRoutine
import com.gradation.lift.feature.routineDetail.routineList.data.model.RoutineIdInfo
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListInfoState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@Composable
internal fun RoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutineList: List<RecentUsedRoutineSetRoutine>,
    routineListInfoState: RoutineListInfoState,
    routineListScreenState: RoutineListScreenState,
    navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .padding(horizontal = LiftTheme.space.space20),
        state = routineListScreenState.lazyListState,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        item { Spacer(modifier = modifier.padding(LiftTheme.space.space4)) }
        items(routineSetRoutineList) { routineSetRoutine ->
            LiftDefaultContainer(modifier = modifier
                .fillMaxWidth()
                .animateItemPlacement()) {
                Column {
                    Row(
                        modifier = modifier
                            .noRippleClickable {
                                navigateRoutineListToRoutineInRoutineDetailGraph(routineSetRoutine.routineSetRoutine.id)
                            }
                            .padding(LiftTheme.space.space12),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                    ) {
                        GlideImage(
                            modifier = modifier.size(LiftTheme.space.space68),
                            model = routineSetRoutine.routineSetRoutine.picture,
                            contentDescription = "${routineSetRoutine.routineSetRoutine.picture}routinePicture"
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = routineSetRoutine.routineSetRoutine.name,
                                    color =
                                    LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                ) {
                                    routineSetRoutine.routineSetRoutine.label.forEach {
                                        RoutineLabel(
                                            modifier.size(LiftTheme.space.space10),
                                            id = it.id
                                        )
                                    }
                                }
                            }
                            if (routineSetRoutine.routineSetRoutine.description.isEmpty())
                                Spacer(modifier = modifier.padding(LiftTheme.space.space4))
                            else
                                LiftText(
                                    textStyle = LiftTextStyle.No6,
                                    text = routineSetRoutine.routineSetRoutine.description,
                                    color =
                                    LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )

                            FlowRow(
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                            ) {
                                if (routineSetRoutine.recentUsed)
                                    RecentRoutineLabel(modifier)
                                if (routineSetRoutine.routineSetRoutine.weekday.size == getWeekdayEntries().size)
                                    AllRoutineLabel(modifier)
                                else {
                                    routineSetRoutine.routineSetRoutine.weekday.forEach {
                                        when (it) {
                                            is Weekday.Friday -> FridayRoutineLabel(modifier)
                                            is Weekday.Monday -> MondayRoutineLabel(modifier)
                                            is Weekday.Saturday -> SaturdayRoutineLabel(modifier)
                                            is Weekday.Sunday -> SundayRoutineLabel(modifier)
                                            is Weekday.Thursday -> ThursdayRoutineLabel(modifier)
                                            is Weekday.Tuesday -> TuesdayRoutineLabel(modifier)
                                            is Weekday.Wednesday -> WednesdayRoutineLabel(modifier)
                                            is Weekday.None -> {}
                                        }
                                    }
                                }
                            }
                        }
                    }
                    HorizontalDivider(
                        modifier = modifier,
                        thickness = LiftTheme.space.space4,
                        color = LiftTheme.colorScheme.no17,
                    )
                    routineSetRoutine.routineSetRoutine.routine.forEach { routine ->
                        Column(
                            modifier = modifier.padding(
                                vertical = LiftTheme.space.verticalPaddingSpace
                            )
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = LiftTheme.space.horizontalPaddingSpace,
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = routine.workCategoryName,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Left
                                )

                                if (routineListInfoState.isContains(
                                        routineSetRoutine.routineSetRoutine.id,
                                        routine.id
                                    )
                                ) {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            routineListInfoState.closeRoutineInfo(
                                                RoutineIdInfo(
                                                    routineSetRoutine.routineSetRoutine.id,
                                                    routine.id
                                                )
                                            )
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronUp),
                                        contentDescription = "${routine}ChevronUp"
                                    )
                                } else {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            routineListInfoState.openRoutineInfo(
                                                RoutineIdInfo(
                                                    routineSetRoutine.routineSetRoutine.id,
                                                    routine.id
                                                )
                                            )
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronDown),
                                        contentDescription = "${routine}ChevronDown"
                                    )
                                }
                            }
                            AnimatedVisibility(
                                routineListInfoState.isContains(
                                    routineSetRoutine.routineSetRoutine.id,
                                    routine.id
                                )
                            ) {
                                RoutineDetailView(
                                    modifier,
                                    routine,
                                )
                            }
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = modifier.padding(LiftTheme.space.space4)) }
    }
}

/**
 * 루틴 상세정보를 펼쳤을 때 나타나는 뷰
 * @since 2024-01-08 20:23:19
 */
@Composable
internal fun RoutineDetailView(
    modifier: Modifier = Modifier,
    routine: Routine,
) {
    Column(
        modifier = modifier.padding(LiftTheme.space.space12),
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
                            color = LiftTheme.colorScheme.no2,
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
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}