package com.gradation.lift.feature.badge.badge.component.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.feature.badge.badge.data.model.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    modifier: Modifier = Modifier,
    sortType: SortType,
    updateSortType: (SortType) -> Unit,
    updateVisibleSortBottomSheet: (Boolean) -> Unit,
) {
    LiftBottomSheet(
        modifier=modifier,
        onDismissRequest = { updateVisibleSortBottomSheet(false) }
    ) {
        Column {


        }
    }
}

