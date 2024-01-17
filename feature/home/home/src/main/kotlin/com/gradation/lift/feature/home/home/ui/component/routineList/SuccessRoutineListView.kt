package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.AllRoutineLabel
import com.gradation.lift.designsystem.component.label.FridayRoutineLabel
import com.gradation.lift.designsystem.component.label.MondayRoutineLabel
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.label.SaturdayRoutineLabel
import com.gradation.lift.designsystem.component.label.SundayRoutineLabel
import com.gradation.lift.designsystem.component.label.ThursdayRoutineLabel
import com.gradation.lift.designsystem.component.label.TuesdayRoutineLabel
import com.gradation.lift.designsystem.component.label.WednesdayRoutineLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.ui.component.RoutineListHeaderView
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.modifier.noRippleClickable


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)
fun LazyListScope.successRoutineListView(
    modifier: Modifier = Modifier,
    routineList: List<RoutineSetRoutine>,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
) {
    item { RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph) }
    items(routineList) {
        LiftDefaultContainer(
            modifier = modifier
                .fillMaxWidth()
                .noRippleClickable {
                    navigateHomeGraphToRoutineDetailRoutineRouter(
                        it.id
                    )
                },
            horizontalPadding = LiftTheme.space.space12,
            verticalPadding = LiftTheme.space.space8,
        ) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
            ) {
                GlideImage(
                    modifier = modifier.size(LiftTheme.space.space68),
                    model = it.picture,
                    contentDescription = "${it.picture}routinePicture"
                )
                Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No3, text = it.name, color =
                            LiftTheme.colorScheme.no9, textAlign = TextAlign.Start
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                        ) {
                            it.label.forEach {
                                RoutineLabel(
                                    modifier.size(LiftTheme.space.space10),
                                    id = it.id
                                )
                            }
                        }
                    }
                    if (it.description.isEmpty())
                        Spacer(modifier = modifier.padding(4.dp))
                    else
                        LiftText(
                            textStyle = LiftTextStyle.No6, text = it.description, color =
                            LiftTheme.colorScheme.no9, textAlign = TextAlign.Start
                        )

                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                    ) {
                        if (it.weekday.size == getWeekdayEntries().size)
                            AllRoutineLabel(modifier)
                        else {
                            it.weekday.forEach {
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
        }
    }
    item {
        Column(modifier = modifier.padding(bottom = LiftTheme.space.space16)) {
            LiftSolidButton(
                modifier = modifier.fillMaxWidth(),
                text = "루틴 추가하기",
                onClick = navigateMainGraphToCreateRoutineGraph,
            )
        }

    }
}