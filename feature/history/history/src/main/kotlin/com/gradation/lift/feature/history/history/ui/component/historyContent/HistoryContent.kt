package com.gradation.lift.feature.history.history.ui.component.historyContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.tab.LiftDoubleTab
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    selectedHistory: History,
    navigateHistoryToUpdateInfoInHistoryGraph: (String, Int, Int, String) -> Unit,
    historyScreenState: HistoryScreenState,
) {
    Column(
        modifier = modifier.padding(bottom = LiftTheme.space.space16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space44)
    ) {
        Column(
            modifier = modifier.padding(horizontal = LiftTheme.space.space20),
        ) {
            LiftDoubleTab(
                modifier = modifier,
                valueList = listOf("운동결과", "메모"),
                selectedIndex = historyScreenState.selectedIndex,
                updateSelected = historyScreenState.updateSelectedIndex
            )
        }
        if (historyScreenState.selectedIndex == 0)
            HistoryWorkResultContent(modifier, selectedHistory)
        else HistoryMemoContent(
            modifier,
            selectedHistory,
            navigateHistoryToUpdateInfoInHistoryGraph
        )

    }
}