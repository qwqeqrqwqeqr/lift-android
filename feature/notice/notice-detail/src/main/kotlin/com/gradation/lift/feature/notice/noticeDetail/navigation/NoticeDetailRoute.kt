package com.gradation.lift.feature.notice.noticeDetail.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.notice.noticeDetail.data.NoticeUiState
import com.gradation.lift.feature.notice.noticeDetail.data.NotificationNoticeDetailViewModel
import com.gradation.lift.feature.notice.noticeDetail.ui.NoticeDetailScreen

@Composable
fun NoticeDetailRoute(
    modifier: Modifier = Modifier,
    navigateNoticeDetailToNoticeInNoticeGraph: () -> Unit,
    viewModel: NotificationNoticeDetailViewModel = hiltViewModel(),
) {

    val noticeUiState: NoticeUiState by viewModel.noticeUiState.collectAsStateWithLifecycle()
    BackHandler(onBack = navigateNoticeDetailToNoticeInNoticeGraph)

    NoticeDetailScreen(
        modifier,
        noticeUiState,
        navigateNoticeDetailToNoticeInNoticeGraph,

    )

}
