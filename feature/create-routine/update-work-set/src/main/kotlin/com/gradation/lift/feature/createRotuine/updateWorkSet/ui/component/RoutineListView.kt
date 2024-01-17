package com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.keypad.LiftKeypadTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.KeypadState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.KeypadWorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.filter.LiftAddContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RoutineListView(
    modifier: Modifier = Modifier,
    keypadWorkSetState: KeypadWorkSetState,
    workSetState: WorkSetState,
    keypadState: KeypadState,
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
            LiftAddContainer(
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
                itemsIndexed(workSetState.workSetList) { index, workSet ->
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
                                text = "${workSet.setNumber}",
                                textStyle = LiftTextStyle.No3,
                                color = LiftTheme.colorScheme.no2,
                                textAlign = TextAlign.Center,
                            )
                            LiftKeypadTextField(
                                modifier = modifier
                                    .weight(3f)
                                    .noRippleClickable {
                                        keypadState.updateState(KeypadWorkSetState.Weight)
                                        keypadState.init(index, workSet)
                                    },
                                value = workSet.weight,
                                focused = keypadWorkSetState is KeypadWorkSetState.Weight && keypadState.selectedIndex.value == index
                            )
                            LiftKeypadTextField(
                                modifier = modifier
                                    .weight(3f)
                                    .noRippleClickable {
                                        keypadState.updateState(KeypadWorkSetState.Repetition)
                                        keypadState.init(index, workSet)
                                    },
                                value = workSet.repetition,
                                focused = keypadWorkSetState is KeypadWorkSetState.Repetition && keypadState.selectedIndex.value == index
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

