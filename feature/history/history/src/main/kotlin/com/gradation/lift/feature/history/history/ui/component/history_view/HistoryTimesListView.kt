package com.gradation.lift.feature.history.history.ui.component.history_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryTimesListView(
    modifier:Modifier=Modifier,
    selectedHistoryList: List<History>,
    selectedHistory: History,
    updateSelectedHistoryIndex: (Int) -> Unit,
){
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
    ) {
        itemsIndexed(selectedHistoryList) { index, item ->
            LiftDefaultChip(
                modifier = modifier,
                text = "${index + 1}회",
                isSelected = item == selectedHistory,
                onClick = { updateSelectedHistoryIndex(index) },
            )
        }
    }
}