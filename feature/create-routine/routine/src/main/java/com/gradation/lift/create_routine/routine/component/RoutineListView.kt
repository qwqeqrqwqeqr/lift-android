package com.gradation.lift.create_routine.routine.component

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.create_routine.routine.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    workSetList: State<List<IndexWorkSet>>,
    updateWorkSet: (IndexWorkSet) -> Unit,
    addWorkSet: () -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
    focusManager: FocusManager
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Set",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Text(
                text = "Kg",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Text(
                text = "Reps",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
                modifier = modifier.weight(2f)
            )
            Spacer(
                modifier = modifier.weight(1f)
            )
        }
        Spacer(modifier = modifier.padding(4.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(workSetList.value) { index, workSet ->
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
                            .weight(2f).align(CenterVertically)
                    )
                    LiftTextField(
                        value = workSet.weight,
                        onValueChange = {
                            updateWorkSet(workSet.copy(weight = it))
                        },
                        modifier = modifier.weight(2f),
                        placeholder = {
                            Text(
                                text = "",
                                style = LiftTheme.typography.no6,
                            )
                        },

                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                        ),
                        textStyle = LiftTheme.typography.no3.copy(
                            textAlign = TextAlign.Center
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                updateWorkSet(
                                    workSet.copy(
                                        weight = workSet.weight.toFloatOrNull()?.toString() ?: "10"
                                    )
                                )
                                focusManager.clearFocus()
                            },
                        ),
                    )

                    LiftTextField(
                        value = workSet.repetition,
                        onValueChange = {
                            updateWorkSet(workSet.copy(repetition = it))
                        },
                        modifier = modifier.weight(2f),
                        placeholder = {
                            Text(
                                text = "",
                                style = LiftTheme.typography.no6,
                            )
                        },

                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                        ),
                        textStyle = LiftTheme.typography.no3.copy(
                            textAlign = TextAlign.Center
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                updateWorkSet(
                                    workSet.copy(
                                        repetition = workSet.repetition.toIntOrNull()?.toString() ?: "12"
                                    )
                                )
                                focusManager.clearFocus()
                            },
                        ),
                    )
                    IconButton(
                        onClick = { removeWorkSet(workSet) },
                        modifier = modifier
                            .size(18.dp)
                            .weight(1f)
                            .align(CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.Trash),
                            contentDescription = "",
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
        }
        Spacer(modifier = modifier.padding(4.dp))
        LiftOutlineButton(
            modifier = modifier
                .height(32.dp)
                .align(Alignment.End),
            contentPadding = PaddingValues(
                start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
            ),
            onClick = addWorkSet,
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

}

