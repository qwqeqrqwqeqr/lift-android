package com.gradation.lift.feature.history.daily_log.component.history_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineFilterChip
import com.gradation.lift.designsystem.theme.LiftTheme
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
            LiftOutlineFilterChip(
                modifier = modifier.padding(vertical = 4.dp),
                text = "${index + 1}회",
                selected = item == selectedHistory,
                onClick = { updateSelectedHistoryIndex(index) },
                selectedTextStyle = LiftTheme.typography.no3,
                unselectedTextStyle = LiftTheme.typography.no4,
            )
        }
    }
}