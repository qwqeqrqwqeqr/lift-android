package com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.chip.LiftFilterChip
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.model.model.work.WorkPart

@Composable
internal fun FilterView(
    modifier: Modifier = Modifier,
    workPartFilter: Set<WorkPart>,
    workPartList: List<WorkPart>,
    filterState: FilterState,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
    ) {
        item {
            LiftFilterChip(
                modifier = modifier.padding(vertical = LiftTheme.space.space4),
                text = "전체",
                selected = workPartList.size == workPartFilter.size || workPartFilter.isEmpty(),
                onClick = { filterState.updateWorkPartFilter(workPartList.toSet()) },
            )
        }
        items(workPartList) {
            LiftFilterChip(
                modifier = modifier.padding(vertical = LiftTheme.space.space4),
                text = it.name,
                selected = workPartFilter.contains(it) && workPartFilter.size != workPartList.size,
                onClick = {
                    if (workPartFilter.contains(it)) {
                        if (workPartFilter.size == workPartList.size) {
                            filterState.updateWorkPartFilter(setOf(it))
                        } else if (workPartFilter.size > 1) {
                            filterState.updateWorkPartFilter(workPartFilter.minus(it))
                        } else  {
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