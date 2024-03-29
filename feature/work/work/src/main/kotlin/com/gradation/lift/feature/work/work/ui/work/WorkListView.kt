package com.gradation.lift.feature.work.work.ui.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckBoxSize
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
import com.gradation.lift.ui.mapper.toText

@Composable
fun WorkSetListView(
    modifier: Modifier = Modifier,
    workRoutineInfoState: WorkRoutineInfoState,
    workState: WorkState,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space4),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space36),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = "Set",
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = modifier.weight(1f),
                        text = "Kg",
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = modifier.weight(1f),
                        text = "Reps",
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                }
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier,
                        text = "완료",
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = modifier.padding(LiftTheme.space.space8))
                }
            }
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                workState.getCurrentWorkRoutine().let { workRoutine ->
                    itemsIndexed(workRoutine.workSetList,
                        key = { _, workSet -> workSet.id }) { set, workSet ->
                        LiftPrimaryContainer(
                            modifier = modifier.fillMaxWidth(),
                            verticalPadding = LiftTheme.space.space8,
                            horizontalPadding = LiftTheme.space.space4
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space36),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No2,
                                        text = "${set + 1}",
                                        color = LiftTheme.colorScheme.no10,
                                        textAlign = TextAlign.Center
                                    )
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No2,
                                        text = workSet.weight.toFloatOrNull()?.toText()
                                            ?: workSet.weight,
                                        color = LiftTheme.colorScheme.no10,
                                        textAlign = TextAlign.Center
                                    )
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No2,
                                        text = workSet.repetition,
                                        color = LiftTheme.colorScheme.no10,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    LiftCircleCheckbox(
                                        modifier = modifier,
                                        checked = workRoutineInfoState.isChecked(
                                            workRoutine.workRoutineId,
                                            workSet.id
                                        ),
                                        liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size28,
                                        onCheckedChange = {
                                            if (workRoutineInfoState.isChecked(
                                                    workRoutine.workRoutineId,
                                                    workSet.id
                                                )
                                            )
                                                workRoutineInfoState.uncheckWorkSet(
                                                    CheckedWorkSetInfo(
                                                        workRoutine.workRoutineId,
                                                        workSet.id
                                                    )
                                                )
                                            else workRoutineInfoState.checkWorkSet(
                                                CheckedWorkSetInfo(
                                                    workRoutine.workRoutineId,
                                                    workSet.id
                                                )
                                            )
                                        }
                                    )
                                    Spacer(modifier = modifier.padding(LiftTheme.space.space8))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


