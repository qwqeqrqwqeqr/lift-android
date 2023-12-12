package com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FilterState

@Composable
internal fun SearchView(
    modifier: Modifier = Modifier,
    searchText: String,
    filterState: FilterState,
) {

    Column {
        LiftSearchTextField(
            modifier = modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = filterState.updateSearchText,
            placeholder = {
                Text(
                    text = "찾으시는 운동을 검색해주세요",
                    color = LiftTheme.colorScheme.no2,
                    style = LiftTheme.typography.no6,
                )
            }
        )
    }

}