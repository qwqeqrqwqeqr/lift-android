package com.gradation.lift.feature.workReady.routineSelection.ui.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.selector.LiftDefaultSelector
import com.gradation.lift.designsystem.component.selector.LiftIconSelector
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.SortFilterState
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 라벨 필터링 바텀 시트
 * @since 2024-01-08 19:56:23
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LabelFilterBottomSheet(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { routineListScreenState.updateLabelFilterTypeBottomSheetView(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(LiftTheme.space.space20)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    LiftMultiStyleText(
                        defaultColor = LiftTheme.colorScheme.no9,
                        defaultTextStyle = LiftTextStyle.No2,
                        textAlign = TextAlign.Start,
                        textWithStyleList = listOf(
                            TextWithStyle("어떻게 "),
                            TextWithStyle("분류", color = LiftTheme.colorScheme.no4),
                            TextWithStyle("할까요?"),
                        )
                    )
                }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space10)
                        .noRippleClickable {
                            routineListScreenState.updateLabelFilterTypeBottomSheetView(false)
                        },
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LiftDefaultSelector(
                        modifier.weight(1f),
                        "전체",
                        labelFilterType.isCheckedAllLabel()
                    ) { sortFilterState.updateAllLabelFilter() }
                    LabelButton(
                        modifier.weight(1f),
                        labelFilterType,
                        sortFilterState,
                        Label.LABEL1
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LabelButton(
                        modifier.weight(1f),
                        labelFilterType,
                        sortFilterState,
                        Label.LABEL2
                    )
                    LabelButton(
                        modifier.weight(1f),
                        labelFilterType,
                        sortFilterState,
                        Label.LABEL3
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LabelButton(
                        modifier.weight(1f),
                        labelFilterType,
                        sortFilterState,
                        Label.LABEL4
                    )
                    LabelButton(
                        modifier.weight(1f),
                        labelFilterType,
                        sortFilterState,
                        Label.LABEL5
                    )
                }
            }
        }
    }
}


@Composable
internal fun LabelButton(
    modifier: Modifier = Modifier,
    labelFilterType: LabelFilterType,
    sortFilterState: SortFilterState,
    label: Label,
) {
    LiftIconSelector(
        modifier = modifier,
        icon = { RoutineLabel(id = label.id) },
        isSelected = labelFilterType.contains(label) && !labelFilterType.isCheckedAllLabel()
    ) {
        if (labelFilterType.isCheckedAllLabel()) {
            sortFilterState.updateLabelFilter(setOf(label))
        } else {
            if (labelFilterType.contains(label)) {
                if (labelFilterType.labelSet.size != 1) {
                    sortFilterState.updateLabelFilter(
                        labelFilterType.labelSet.minus(label)
                    )
                }else{
                    sortFilterState.updateAllLabelFilter()
                }
            } else {
                sortFilterState.updateLabelFilter(
                    labelFilterType.labelSet.plus(label)
                )
            }
        }
    }
}