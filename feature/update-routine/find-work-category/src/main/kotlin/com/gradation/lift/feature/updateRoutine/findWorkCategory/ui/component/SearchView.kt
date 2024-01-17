package com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.gradation.lift.designsystem.component.textField.LiftSearchInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FindWorkCategoryScreenState

@Composable
internal fun SearchView(
    modifier: Modifier = Modifier,
    searchText: String,
    filterState: FilterState,
    findWorkCategoryScreenState: FindWorkCategoryScreenState,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = LiftTheme.space.space20)
    ) {

        LiftSearchInputTextField(
            modifier = modifier,
            value = searchText,
            placeHolderValue = "찾으시는 운동을 검색해주세요",
            onValueChange = filterState.updateSearchText,
            onValueClear = filterState.clearSearchText,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { findWorkCategoryScreenState.focusManager.clearFocus() }
            )
        )
    }
}