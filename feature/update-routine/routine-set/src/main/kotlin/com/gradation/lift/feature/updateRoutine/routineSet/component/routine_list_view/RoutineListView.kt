package com.gradation.lift.feature.updateRoutine.routineSet.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.ui.mapper.toText


/**
 * [RoutineListView]
 * 루틴이 추가 되었을 때의 뷰
 * @since 2023-08-21 13:37:13
 */
@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutine: List<UpdateRoutine>,
    removeRoutine: (UpdateRoutine) -> Unit,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterVertically),
            text = "루틴",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no3
        )

        LiftOutlineButton(
            modifier = modifier
                .height(32.dp),
            contentPadding = PaddingValues(
                start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
            ),
            onClick = navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
        ) {
            Text(
                text = "추가",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.Plus),
                contentDescription = null,
            )
        }
    }


    Spacer(modifier = modifier.padding(8.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        routineSetRoutine.forEach { routine ->
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(16.dp)
                    .fillMaxWidth()
                , verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = routine.workCategory,
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        modifier = modifier.weight(1f)
                    )
                    Row(
                        modifier = modifier.weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.Trash),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = modifier.noRippleClickable
                                 { removeRoutine(routine) }

                        )
                        Spacer(modifier = modifier.padding(12.dp))
                        Icon(
                            painter = painterResource(LiftIcon.Order),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = modifier
                        )
                    }
                }
                Spacer(modifier = modifier.padding(3.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = modifier.padding(horizontal = 12.dp)
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
                Spacer(modifier = modifier.padding(3.dp))
                routine.workSetList.forEachIndexed { index, workSet ->
                    Row(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 1.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Text(
                            text = "${index + 1}",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .padding(3.dp)
                        )

                        Text(
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            text = workSet.weight.toText(),
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 6.dp)
                                )
                                .padding(3.dp)

                        )

                        Text(
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9,
                            text = workSet.repetition.toString(),
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .weight(1f)
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 6.dp)
                                )
                                .padding(3.dp)

                        )
                    }
                }
            }


        }
    }

}


