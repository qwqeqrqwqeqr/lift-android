package com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routineDetail.routineList.data.model.LabelFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.feature.routineDetail.routineList.data.state.SortFilterState
import com.gradation.lift.model.model.routine.Label

/**
 * 라벨 필터링 바텀 시트
 * @since 2023-12-03 22:47:07
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
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.search_3d),
                        contentDescription = "filterSearch"
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("어떻게 ")
                            withStyle(
                                style = SpanStyle(color = LiftTheme.colorScheme.no4),
                            ) {
                                append("분류")
                            }
                            append("할까요?")
                        },
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9
                    )
                }
                IconButton(
                    modifier = modifier.size(16.dp),
                    onClick = { routineListScreenState.updateLabelFilterTypeBottomSheetView(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "",
                        tint = LiftTheme.colorScheme.no9,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                    LiftButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = { sortFilterState.updateAllLabelFilter() },
                        containerColor = if (labelFilterType.isCheckedAllLabel()) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1,
                        contentColor = if (labelFilterType.isCheckedAllLabel()) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9
                    ) {
                        Text(
                            text = "전체",
                            style = if (labelFilterType.isCheckedAllLabel()) LiftTheme.typography.no3 else LiftTheme.typography.no4,
                        )
                    }
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
    label: Label
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            if (labelFilterType.isCheckedAllLabel()) {
                sortFilterState.updateLabelFilter(setOf(label))
            } else {
                if (labelFilterType.contains(label)) {
                    if (labelFilterType.labelSet.size != 1) {
                        sortFilterState.updateLabelFilter(
                            labelFilterType.labelSet.minus(label)
                        )
                    }
                } else {
                    sortFilterState.updateLabelFilter(
                        labelFilterType.labelSet.plus(label)
                    )
                }
            }
        },
        containerColor = if (labelFilterType.contains(label) && !labelFilterType.isCheckedAllLabel()) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no1
    ) {
        RoutineLabel(id = label.id)
    }
}