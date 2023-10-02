package com.gradation.lift.feature.history.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.component.EmptyHistoryView
import com.gradation.lift.feature.history.history.component.HistoryView
import com.gradation.lift.feature.history.history.data.HistoryHistoryViewModel
import com.gradation.lift.feature.history.history.data.HistoryUiState
import com.gradation.lift.feature.history.history.data.TabDestination
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryHistoryRoute(
    modifier: Modifier = Modifier,
    navigateHistoryGraphToWorkGraph: () -> Unit,
    viewModel: HistoryHistoryViewModel = hiltViewModel(),
) {

    val tabScreenList: List<TabDestination> =
        listOf(TabDestination.Analytics, TabDestination.DailyLog)
    val pagerState = rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabScreenList.size })
    val coroutineScope = rememberCoroutineScope()

    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()

    HistoryHistoryScreen(
        modifier,
        tabScreenList,
        pagerState,
        coroutineScope,
        navigateHistoryGraphToWorkGraph,
        historyUiState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryHistoryScreen(
    modifier: Modifier = Modifier,
    tabDestination: List<TabDestination>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navigateHistoryGraphToWorkGraph: () -> Unit,
    historyUiState: HistoryUiState
) {

    Surface(
        color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()
    ) {
        when (historyUiState) {
            HistoryUiState.Empty -> {
                EmptyHistoryView(
                    modifier, navigateHistoryGraphToWorkGraph
                )
            }

            HistoryUiState.None -> {

            }

            HistoryUiState.Success -> {
                HistoryView(modifier, tabDestination, pagerState, coroutineScope)
            }
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
