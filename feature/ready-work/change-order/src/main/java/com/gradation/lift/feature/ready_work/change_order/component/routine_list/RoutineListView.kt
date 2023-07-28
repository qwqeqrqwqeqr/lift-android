package com.gradation.lift.feature.ready_work.change_order.component.routine_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.change_order.ReadyWorkChangeOrderScreen
import com.gradation.lift.feature.ready_work.change_order.data.RoutineSetRoutineUiState
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList


@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutine: List<RoutineSetRoutine>,
    onDeleteRoutineSetRoutineList: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(routineSetRoutine) { index, routineSetRoutine ->
            Row(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    NumberCircle(
                        number = index + 1,
                        modifier = modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.padding(8.dp))

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier.align(Alignment.CenterVertically)
                    ) {
                        Row {
                            Text(
                                text = routineSetRoutine.name,
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no9,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Start,
                                maxLines = 1
                            )
                            Spacer(modifier = modifier.padding(4.dp))
                            Icon(
                                painter = painterResource(LiftIcon.Trash),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = modifier
                                    .clickable(
                                        onClick = {
                                            onDeleteRoutineSetRoutineList(
                                                routineSetRoutine.id
                                            )
                                        }
                                    )
                                    .align(Alignment.CenterVertically)

                            )
                        }
                        Text(
                            text = routineSetRoutine.description,
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }

                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(LiftIcon.Order),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .pointerInput(Unit) {

                                detectDragGesturesAfterLongPress(
                                    onDrag = { _, _ -> }
                                )
                            }
                    )
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun RoutineListViewPreview() {
    LiftMaterialTheme {
        ReadyWorkChangeOrderScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickStartWork = {},
            onDeleteRoutineSetRoutineList = {},
            RoutineSetRoutineUiState.Success(
                routineSetRoutine = routineSetRoutineModelList
            ),
        )
    }
}