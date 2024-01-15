package com.gradation.lift.feature.notice.notice.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.notice.GetNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * [NoticeViewModel]
 * @property noticeUiState 공지사항 불러오기 관련 상태
 * @since 2024-01-15 17:13:22
 */
@HiltViewModel
class NoticeViewModel @Inject constructor(
    getNoticeUseCase: GetNoticeUseCase,
) : ViewModel() {

    val noticeUiState: StateFlow<NoticeUiState> = getNoticeUseCase().map {
        when (it) {
            is DataState.Fail -> NoticeUiState.Empty
            is DataState.Success -> {
                if (it.data.isEmpty()) {
                    NoticeUiState.Empty
                } else {
                    NoticeUiState.Success(it.data.sortedByDescending { notice -> notice.date })
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NoticeUiState.Loading
    )
}