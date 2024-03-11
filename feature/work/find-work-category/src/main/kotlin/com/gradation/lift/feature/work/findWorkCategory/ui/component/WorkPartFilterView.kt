package com.gradation.lift.feature.work.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.findWorkCategory.data.state.FilterState
import com.gradation.lift.model.model.work.WorkPart

@Composable
internal fun WorkPartFilterView(
    modifier: Modifier = Modifier,
    workPartFilter: Set<WorkPart>,
    workPartList: List<WorkPart>,
    filterState: FilterState,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
    ) {
        item {
            Spacer(modifier = modifier.width(LiftTheme.space.space20))
            LiftDefaultChip(
                modifier = modifier,
                text = "전체",
                isSelected = workPartList.size == workPartFilter.size || workPartFilter.isEmpty(),
                onClick = { filterState.updateWorkPartFilter(workPartList.toSet()) },
            )
        }
        items(workPartList) {
            LiftDefaultChip(
                modifier = modifier,
                text = it.name,
                isSelected = workPartFilter.contains(it) && workPartFilter.size != workPartList.size,
                onClick = {
                    if (workPartFilter.contains(it)) {
                        if (workPartFilter.size == workPartList.size) {
                            filterState.updateWorkPartFilter(setOf(it))
                        } else if (workPartFilter.size > 1) {
                            filterState.updateWorkPartFilter(workPartFilter.minus(it))
                        } else {
                            filterState.updateWorkPartFilter(workPartList.toSet())
                        }
                    } else {
                        filterState.updateWorkPartFilter(workPartFilter.plus(it))
                    }
                }
            )
        }
    }
}