package com.gradation.lift.feature.notification.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notification.notification.data.TabDestination
import com.gradation.lift.navigation.Router.HOME_HOME_ROUTER_NAME
import com.gradation.lift.navigation.Router.MY_INFO_MY_INFO_ROUTER_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationNotificationRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateNotificationGraphToPreGraph: () -> Unit,
    viewModel: NotificationNotificationViewModel = hiltViewModel(),
) {


    val tabScreenList: List<TabDestination> =
        listOf(TabDestination.Push(), TabDestination.Notice())

    val pagerState: PagerState =
        rememberPagerState(initialPage = when (navController.previousBackStackEntry?.destination?.route.toString()) {
            HOME_HOME_ROUTER_NAME -> 0
            MY_INFO_MY_INFO_ROUTER_NAME -> 1
            else -> 0
        },
            initialPageOffsetFraction = 0f,
            pageCount = { tabScreenList.size })

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    NotificationNotificationScreen(
        modifier, tabScreenList, pagerState, coroutineScope,
        navigateNotificationGraphToPreGraph
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationNotificationScreen(
    modifier: Modifier = Modifier,
    tabScreenList: List<TabDestination>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navigateNotificationGraphToPreGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내 소식",
                onBackClickTopBar = navigateNotificationGraphToPreGraph
            )
        }

    ) {
        Surface(
            color = LiftTheme.colorScheme.no5, modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
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
                    tabScreenList.forEachIndexed { index, item ->
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
                    tabScreenList[index].screen()
                }
            }
        }
    }
}

