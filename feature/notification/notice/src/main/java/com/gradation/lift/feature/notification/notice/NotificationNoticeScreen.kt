package com.gradation.lift.feature.notification.notice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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
        navigateNotificationToNoticeDetailInNotificationGraph
    )
}

@Composable
fun NotificationNoticeScreen(
    modifier: Modifier = Modifier,
    noticeUiState: NoticeUiState,
    navigateNotificationToNoticeDetailInNotificationGraph: () -> Unit
) {

}

