package com.gradation.lift.feature.work.work.ui.work

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.model.WorkRoutineIdInfo
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun WorkSetListView(
    modifier: Modifier = Modifier,
    currentWork: WorkRoutine?,
    workState: WorkState,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Set",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Kg",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Reps",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "완료",
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)
        ) {
            currentWork?.let { work ->
                itemsIndexed(work.workSetList) { workSetIndex,workSet ->
                    Row(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no1, RoundedCornerShape(LiftTheme.space.space8))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Text(
                            text = "${workSetIndex+1}",
                            style = LiftTheme.typography.no2,
                            color = if (workState.isChecked(
                                    work.id,
                                    workSetIndex
                                )
                            ) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = workSet.weight.toFloatOrNull()?.toText() ?: workSet.weight,
                            style = LiftTheme.typography.no2,
                            color = if (workState.isChecked(
                                    work.id,
                                    workSetIndex
                                )
                            ) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )

                        Text(
                            text = workSet.repetition,
                            style = LiftTheme.typography.no2,
                            color = if (workState.isChecked(
                                    work.id,
                                    workSetIndex
                                )
                            ) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )
                        if (workState.isChecked(work.id, workSetIndex)
                        ) {
                            Icon(
                                modifier = modifier
                                    .size(LiftTheme.space.space28)
                                    .weight(1f)
                                    .noRippleClickable {
                                        workState.uncheckWorkSet(
                                            WorkRoutineIdInfo(
                                                work.id,
                                                workSetIndex
                                            )
                                        )
                                    },
                                painter = painterResource(LiftIcon.CheckBoxChecked),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        } else {
                            Icon(
                                modifier = modifier
                                    .size(LiftTheme.space.space28)
                                    .weight(1f)
                                    .noRippleClickable {
                                        workState.checkWorkSet(
                                            WorkRoutineIdInfo(
                                                work.id,
                                                workSetIndex
                                            )
                                        )
                                    },
                                painter = painterResource(LiftIcon.CheckBoxUnChecked),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
                    }
                }
            }
        }
    }
}
