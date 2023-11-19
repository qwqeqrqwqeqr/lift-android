package com.gradation.lift.feature.work.routine_selection.component.routine_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.ui.mapper.toText


@Composable
fun RoutineDetailView(
    modifier: Modifier = Modifier,
    routine: Routine,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Set",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Kg",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
            Text(
                text = "Reps",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(1f)
            )
        }
        Spacer(modifier = modifier.padding(4.dp))
        routine.workSetList.forEachIndexed { index, workSet ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Box(
                    modifier = modifier
                        .weight(1f)
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(
                                size = 6.dp
                            )
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = modifier
                        .weight(1f)
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(
                                size = 6.dp
                            )
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = workSet.weight.toText(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = modifier
                        .weight(1f)
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(
                                size = 6.dp
                            )
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${workSet.repetition}",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = modifier.padding(4.dp))
        }
    }
}