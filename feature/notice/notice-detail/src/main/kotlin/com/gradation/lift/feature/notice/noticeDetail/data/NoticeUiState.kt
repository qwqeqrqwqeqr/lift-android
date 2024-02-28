package com.gradation.lift.feature.notice.noticeDetail.data

import com.gradation.lift.model.model.notice.Notice

sealed interface NoticeUiState {
    data class Success(val notice: Notice) : NoticeUiState
    data class Fail(val message:String) : NoticeUiState
    data object Loading : NoticeUiState
}