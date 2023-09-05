package com.gradation.lift.feature.history.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.TabDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryHistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryHistoryViewModel = hiltViewModel(),
) {

    val tabScreenList: List<TabDestination> = listOf(TabDestination.Analytics, TabDestination.DailyLog)
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabScreenList.size }
    )
    val coroutineScope = rememberCoroutineScope()

    HistoryHistoryScreen(modifier, tabScreenList, pagerState, coroutineScope)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryHistoryScreen(
    modifier: Modifier = Modifier,
    tabDestination: List<TabDestination>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
) {
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {
        Column(modifier) {
            TabRow(
                modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                selectedTabIndex = pagerState.currentPage,
                indicator = @Composable { tabPositions ->
                    if (pagerState.currentPage < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            color=LiftTheme.colorScheme.no4,
                            height=3.dp,
                            modifier= Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        )
                    }
                },
                divider = {},
                containerColor = LiftTheme.colorScheme.no5,
            ) {
                tabDestination.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },

                        text = {
                            if (pagerState.currentPage == index) {
                                Text(
                                    text = item.title,
                                    color = LiftTheme.colorScheme.no11,
                                    style = LiftTheme.typography.no3
                                )
                            } else {
                                Text(
                                    text = item.title,
                                    color = LiftTheme.colorScheme.no10,
                                    style = LiftTheme.typography.no4
                                )
                            }

                        }
                    )
                }
            }
            HorizontalPager(
                modifier = modifier.fillMaxSize(),
                state = pagerState,
            ) {index ->
                tabDestination[index].screen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun HistoryHistoryScreenPreview() {
    LiftMaterialTheme() {
        HistoryHistoryScreen(
            tabDestination = listOf(TabDestination.Analytics, TabDestination.DailyLog),
            pagerState = rememberPagerState(
                initialPage = 0,
                initialPageOffsetFraction = 0f
            ) {
                2
            },
            coroutineScope = rememberCoroutineScope()
        )
    }
}