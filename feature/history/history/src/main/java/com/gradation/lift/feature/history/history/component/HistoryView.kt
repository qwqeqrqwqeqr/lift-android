package com.gradation.lift.feature.history.history.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.TabDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryView(
    modifier:Modifier=Modifier,
    tabDestination: List<TabDestination>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
){
    Column(modifier) {
        TabRow(
            modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable { tabPositions ->
                if (pagerState.currentPage < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        color = LiftTheme.colorScheme.no4,
                        height = 3.dp,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    )
                }
            },
            divider = {},
            containerColor = LiftTheme.colorScheme.no5,
        ) {
            tabDestination.forEachIndexed { index, item ->
                Tab(selected = pagerState.currentPage == index,
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

                    })
            }
        }
        HorizontalPager(
            modifier = modifier.fillMaxSize(),
            state = pagerState,
        ) { index ->
            tabDestination[index].screen()
        }
    }
}