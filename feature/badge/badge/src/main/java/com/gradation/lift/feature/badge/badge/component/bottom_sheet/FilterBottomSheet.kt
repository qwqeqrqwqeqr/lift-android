package com.gradation.lift.feature.badge.badge.component.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.feature.badge.badge.data.model.FilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    filterType: FilterType,
    updateFilterType: (FilterType) -> Unit,
    updateVisibleFilterBottomSheet: (Boolean) -> Unit,
) {
    LiftBottomSheet(
        modifier=modifier,
        onDismissRequest = { updateVisibleFilterBottomSheet(false) }
    ) {
        Column {

        }
    }
}