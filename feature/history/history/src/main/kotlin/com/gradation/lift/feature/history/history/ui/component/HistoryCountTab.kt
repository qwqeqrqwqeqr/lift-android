package com.gradation.lift.feature.history.history.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.tab.LiftDefaultTab
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryCountTab(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    selectedHistoryList: List<History>,
    updateSelectedTabIndex: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = LiftTheme.space.space20)
    ) {
        LiftDefaultTab(
            modifier = modifier,
            contentList = List(selectedHistoryList.size) { index -> "${index + 1}회차" },
            selectedTabIndex = selectedTabIndex,
            onClick = updateSelectedTabIndex
        )
    }
}