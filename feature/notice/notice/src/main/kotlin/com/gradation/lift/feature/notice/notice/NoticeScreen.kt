package com.gradation.lift.feature.notice.notice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notice.notice.component.EmptyNoticeListView
import com.gradation.lift.feature.notice.notice.component.LoadingNoticeListView
import com.gradation.lift.feature.notice.notice.component.NoticeListView
import com.gradation.lift.feature.notice.notice.data.NoticeUiState
import com.gradation.lift.model.model.notification.Notice


@Composable
fun NoticeScreen(
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

