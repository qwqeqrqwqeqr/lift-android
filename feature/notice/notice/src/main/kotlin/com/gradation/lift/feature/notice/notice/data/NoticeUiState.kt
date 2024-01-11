package com.gradation.lift.feature.notice.notice.data

import com.gradation.lift.model.model.notification.Notice


/**
 * [NoticeUiState]
 * UI에서 보여질 공지사항의 상태, 초기값은 [Loading]으로 설정되어 있음
 * @since 2023-10-03 17:18:23
 */
sealed interface NoticeUiState {
    data class Success(val noticeList: List<Notice>) : NoticeUiState
    data class Fail(val message: String) : NoticeUiState
    object Loading : NoticeUiState
    object Empty : NoticeUiState
}