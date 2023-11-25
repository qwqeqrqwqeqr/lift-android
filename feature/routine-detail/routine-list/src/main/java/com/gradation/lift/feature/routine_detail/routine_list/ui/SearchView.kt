package com.gradation.lift.feature.routine_detail.routine_list.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState


@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    sortFilterState: SortFilterState,
    searchFilterText: String,
) {

    LiftSearchTextField(
        modifier = modifier.fillMaxWidth(),
        value = searchFilterText,
        onValueChange = sortFilterState.updateSearchFilterText,
        placeholder = {
            Text(
                text = "찾으시는 운동을 검색해주세요",
                color = LiftTheme.colorScheme.no2,
                style = LiftTheme.typography.no6,
            )
        }
    )

}