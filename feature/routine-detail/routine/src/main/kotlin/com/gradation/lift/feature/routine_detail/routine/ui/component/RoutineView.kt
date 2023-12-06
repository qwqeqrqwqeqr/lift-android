package com.gradation.lift.feature.routine_detail.routine.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.mapper.toText

@Composable
fun RoutineView(
    modifier: Modifier = Modifier,
    routineSetRoutine: RoutineSetRoutine,
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no17)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
    ) {
        routineSetRoutine.routine.forEach { routine ->
            Column(
                modifier = modifier
                    .shadow(
                        elevation = LiftTheme.space.space8,
                        ambientColor = LiftTheme.colorScheme.no34,
                        spotColor = LiftTheme.colorScheme.no34,
                        shape = RoundedCornerShape(size = LiftTheme.space.space12)
                    )
                    .background(
                        LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(size = LiftTheme.space.space12)
                    )
                    .padding(vertical = LiftTheme.space.verticalPaddingSpace)

            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(LiftTheme.space.paddingSpace),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = routine.workCategory.name,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Left
                    )
                }
                Column(
                    modifier = modifier.padding(LiftTheme.space.paddingSpace),
                    verticalArrangement = Arrangement.spacedBy(
                        LiftTheme.space.space8
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            24.dp
                        )
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
                    routine.workSetList.forEachIndexed { index, workSet ->
                        Row(
                            modifier = modifier
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 6.dp)
                                )
                                .padding(vertical = LiftTheme.space.space12),
                            horizontalArrangement = Arrangement.spacedBy(
                                24.dp
                            )
                        ) {
                            Text(
                                modifier = modifier.weight(1f),
                                text = "${index + 1}",
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no2,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                modifier = modifier.weight(1f),
                                text = workSet.weight.toText(),
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no2,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                modifier = modifier.weight(1f),
                                text = "${workSet.repetition}",
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no2,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}