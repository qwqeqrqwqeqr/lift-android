package com.gradation.lift.feature.history.history.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.HistoryHistoryScreen
import com.gradation.lift.feature.history.history.data.HistoryUiState
import com.gradation.lift.feature.history.history.data.TabDestination


@Composable
fun EmptyHistoryView(
    modifier: Modifier = Modifier,
    navigateHistoryGraphToWorkGraph: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Image(
            modifier = modifier.size(72.dp),
            painter = painterResource(id = R.drawable.analytics),
            contentDescription = "analytics"
        )
        Spacer(modifier = modifier.padding(8.dp))
        Text(
            text = "아직 운동을 하지 않으셨군요",
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no1
        )
        Text(
            text = "운동을 완료하시면 결과를 분석해드려요",
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no4
        )
        Spacer(modifier = modifier.padding(18.dp))
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = navigateHistoryGraphToWorkGraph,
        ) {
            Text(
                text = "운동시작하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight()
                    .width(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun HistoryHistoryScreenPreview() {
    LiftMaterialTheme() {
        HistoryHistoryScreen(
            tabDestination = listOf(TabDestination.Analytics, TabDestination.DailyLog),
            pagerState = rememberPagerState(
                initialPage = 0, initialPageOffsetFraction = 0f
            ) {
                2
            },
            coroutineScope = rememberCoroutineScope(),
            navigateHistoryGraphToWorkGraph = {},
            historyUiState = HistoryUiState.Empty
        )
    }
}
