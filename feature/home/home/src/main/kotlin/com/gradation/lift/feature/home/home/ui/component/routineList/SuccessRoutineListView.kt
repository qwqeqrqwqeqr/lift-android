package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
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
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.model.RecentUsedRoutineSetRoutine
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.ui.modifier.noRippleClickable


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuccessRoutineListView(
    modifier: Modifier = Modifier,
    routineList: List<RecentUsedRoutineSetRoutine>,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
        ) {
            RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                item { Spacer(modifier = modifier.width(LiftTheme.space.space12)) }
                items(routineList, key = { it.routineSetRoutine.id }) {
                    LiftDefaultContainer(
                        modifier = modifier
                            .width(LiftTheme.space.space140)
                            .noRippleClickable {
                                navigateHomeGraphToRoutineDetailRoutineRouter(
                                    it.routineSetRoutine.id
                                )
                            },
                        horizontalPadding = LiftTheme.space.space12,
                        verticalPadding = LiftTheme.space.space8,
                    ) {
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                ) {
                                    it.routineSetRoutine.label.forEach {
                                        RoutineLabel(
                                            modifier.size(LiftTheme.space.space10),
                                            id = it.id
                                        )
                                    }
                                }
                                Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)) {
                                    LiftText(
                                        textStyle = LiftTextStyle.No3,
                                        text = it.routineSetRoutine.name,
                                        color =
                                        LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Start
                                    )
                                    if (it.routineSetRoutine.description.isEmpty())
                                        Spacer(modifier = modifier.height(LiftTheme.space.space16))
                                    else
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = it.routineSetRoutine.description,
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Start,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                }
                            }
                            FlowRow(
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                            ) {
                                if (it.recentUsed) RecentRoutineLabel(modifier)
                                if (it.routineSetRoutine.weekday.size == getWeekdayEntries().size)
                                    AllRoutineLabel(modifier, false)
                                else {
                                    it.routineSetRoutine.weekday.forEach {
                                        when (it) {
                                            is Weekday.Friday -> FridayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Monday -> MondayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Saturday -> SaturdayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Sunday -> SundayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Thursday -> ThursdayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Tuesday -> TuesdayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.Wednesday -> WednesdayRoutineLabel(
                                                modifier,
                                                false
                                            )

                                            is Weekday.None -> {}
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Column(
            modifier = modifier.padding(
                bottom = LiftTheme.space.space16,
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20
            )
        ) {
            LiftSolidButton(
                modifier = modifier.fillMaxWidth(),
                text = "루틴 추가하기",
                onClick = navigateMainGraphToCreateRoutineGraph,
            )
        }
    }
}