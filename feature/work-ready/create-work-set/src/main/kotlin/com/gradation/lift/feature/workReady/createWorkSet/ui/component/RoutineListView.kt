package com.gradation.lift.feature.workReady.createWorkSet.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.button.smallButton.LiftAddSmallButton
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftKeyPadTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutineWorkSet
import com.gradation.lift.feature.workReady.createWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.workReady.createWorkSet.data.state.WorkSetState
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RoutineListView(
    modifier: Modifier = Modifier,
    workSetState: WorkSetState,
    routineScreenState: RoutineScreenState,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "루틴 만들기",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
            LiftAddSmallButton(
                modifier = modifier
                    .noRippleClickable { workSetState.addWorkSet() }
            )

        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = LiftTheme.space.space12),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
                verticalAlignment = CenterVertically
            ) {
                LiftText(
                    modifier = modifier.weight(2f),
                    textStyle = LiftTextStyle.No3,
                    text = "Set",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center
                )
                LiftText(
                    modifier = modifier.weight(3f),
                    textStyle = LiftTextStyle.No3,
                    text = "Kg",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center
                )
                LiftText(
                    modifier = modifier.weight(3f),
                    textStyle = LiftTextStyle.No3,
                    text = "Reps",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = modifier
                        .weight(1f)
                )
            }
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6),
                state = routineScreenState.lazyListState
            ) {
                itemsIndexed(
                    items = workSetState.workSetList,
                    key = { index: Int, _: WorkReadyRoutineWorkSet -> index }) { index, workSet ->
                    LiftPrimaryContainer(
                        modifier = modifier
                            .fillMaxWidth()
                            .animateItemPlacement(),
                        verticalPadding = LiftTheme.space.space8,
                        shape = RoundedCornerShape(size = LiftTheme.space.space8)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(end = LiftTheme.space.space12),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
                            verticalAlignment = CenterVertically
                        ) {
                            LiftText(
                                modifier = modifier.weight(2f),
                                text = "${index+1}",
                                textStyle = LiftTextStyle.No3,
                                color = LiftTheme.colorScheme.no2,
                                textAlign = TextAlign.Center,
                            )
                            LiftKeyPadTextField(
                                modifier = modifier
                                    .height(LiftTheme.space.space28)
                                    .weight(3f),
                                value = workSet.weight,
                                onValueChange = {
                                    workSetState.updateWorkSet(
                                        index,
                                        workSet.copy(weight = it)
                                    )
                                },
                                isError = !decimalNumberValidator(workSet.weight),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = {
                                    routineScreenState.focusManager.clearFocus()
                                })
                            )

                            LiftKeyPadTextField(
                                modifier = modifier
                                    .height(LiftTheme.space.space28)
                                    .weight(3f),
                                value = workSet.repetition,
                                onValueChange = {
                                    workSetState.updateWorkSet(
                                        index,
                                        workSet.copy(repetition = it)
                                    )
                                },
                                isError = !decimalNumberValidator(workSet.repetition) || workSet.repetition == "0",
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = {
                                    routineScreenState.focusManager.clearFocus()
                                })
                            )

                            Icon(
                                modifier = modifier
                                    .weight(1f)
                                    .size(LiftTheme.space.space24)
                                    .noRippleClickable { workSetState.removeWorkSet(workSet) },
                                painter = painterResource(LiftIcon.Cancel),
                                contentDescription = "Remove",
                                tint = Color.Unspecified,
                            )
                        }

                    }
                }
            }
        }
    }
}

