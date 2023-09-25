package com.gradation.lift.feature.update_routine.find_work_category.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftFilterChip
import com.gradation.lift.feature.update_routine.find_work_category.data.model.WorkPartFilterSelection

@Composable
fun FilterView(
    modifier: Modifier = Modifier,
    workPartFilterList: List<WorkPartFilterSelection>,
    updateWorkPartFilter: (String) -> Unit,
) {
    LazyRow(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(workPartFilterList) {
            LiftFilterChip(
                modifier = modifier.padding(vertical = 4.dp),
                text = it.workPart, selected = it.selected,
                onClick = { updateWorkPartFilter(it.workPart) }
            )
        }
    }

}