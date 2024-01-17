package com.gradation.lift.feature.notice.notice.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.notice.notice.NoticeScreen
import com.gradation.lift.feature.notice.notice.data.NoticeUiState
import com.gradation.lift.feature.notice.notice.data.NoticeViewModel

@Composable
fun NoticeRoute(
    modifier: Modifier = Modifier,
    navigateNoticeGraphToMyInfoGraph: () -> Unit,
    navigateNoticeToNoticeDetailInNoticeGraph: (Int) -> Unit,
    viewModel: NoticeViewModel = hiltViewModel(),
) {
    val noticeUiState: NoticeUiState by viewModel.noticeUiState.collectAsStateWithLifecycle()
    BackHandler(onBack = navigateNoticeGraphToMyInfoGraph)

    NoticeScreen(
        modifier,
        noticeUiState,
        navigateNoticeGraphToMyInfoGraph,
        navigateNoticeToNoticeDetailInNoticeGraph
    )
}


