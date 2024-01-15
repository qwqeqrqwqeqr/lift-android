package com.gradation.lift.feature.notice.notice.data

import com.gradation.lift.model.model.notification.Notice

sealed interface NoticeUiState {
    data class Success(val noticeList: List<Notice>) : NoticeUiState
    data object Empty : NoticeUiState
    data object Loading : NoticeUiState
}