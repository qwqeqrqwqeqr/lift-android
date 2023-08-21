package com.gradation.lift.feature.create_routine.find_work_category.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftFilterChip
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.find_work_category.data.model.WorkPartFilterSelection

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    searchText: String,
    workPartFilterList: List<WorkPartFilterSelection>,
    updateSearchText: (String) -> Unit,
    updateWorkPartFilter: (String) -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5,
    ) {
        Column(
            modifier = modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
        ) {
            LiftSearchTextField(
                modifier = modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = updateSearchText,
                placeholder = {
                    Text(
                        text = "찾으시는 운동을 검색해주세요",
                        color = LiftTheme.colorScheme.no2,
                        style = LiftTheme.typography.no6,
                    )
                }
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                items(workPartFilterList) {
                    LiftFilterChip(
                        modifier = modifier.padding(vertical = 16.dp),
                        text = it.workPart, selected = it.selected,
                        onClick = { updateWorkPartFilter(it.workPart) }
                    )
                }
            }
        }
    }
}