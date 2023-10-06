package com.gradation.lift.feature.notification.notice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notification.notice.component.EmptyNoticeListView
import com.gradation.lift.feature.notification.notice.component.LoadingNoticeListView
import com.gradation.lift.feature.notification.notice.component.NoticeListView
import com.gradation.lift.feature.notification.notice.data.NoticeUiState
import com.gradation.lift.feature.notification.notice.data.NotificationNoticeViewModel
import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.navigation.navigation.navigateNotificationToNoticeDetailInNotificationGraph

@Composable
fun NotificationNoticeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NotificationNoticeViewModel = hiltViewModel(),
) {
    val navigateNotificationToNoticeDetailInNotificationGraph: () -> Unit =
        { navController.navigateNotificationToNoticeDetailInNotificationGraph() }

    val noticeUiState: NoticeUiState by viewModel.noticeUiState.collectAsStateWithLifecycle()
    val updateSelectedNotice: (Notice) -> Unit = viewModel.updateSelectedNotice()

    NotificationNoticeScreen(
        modifier,
        noticeUiState,
        updateSelectedNotice,
        navigateNotificationToNoticeDetailInNotificationGraph
    )
}

@Composable
fun NotificationNoticeScreen(
    modifier: Modifier = Modifier,
    noticeUiState: NoticeUiState,
    updateSelectedNotice: (Notice) -> Unit,
    navigateNotificationToNoticeDetailInNotificationGraph: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = LiftTheme.colorScheme.no5,
    ) {
        when (noticeUiState) {
            NoticeUiState.Empty -> {
                EmptyNoticeListView(modifier)
            }
            is NoticeUiState.Fail -> {}
            NoticeUiState.Loading -> {
                LoadingNoticeListView(modifier)
            }

            is NoticeUiState.Success -> {
                NoticeListView(
                    modifier,
                    noticeUiState.noticeList,
                    updateSelectedNotice,
                    navigateNotificationToNoticeDetailInNotificationGraph
                )
            }
        }

    }
}

