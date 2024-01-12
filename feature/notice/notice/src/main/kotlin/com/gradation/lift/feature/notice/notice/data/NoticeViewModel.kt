package com.gradation.lift.feature.notice.notice.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.notice.GetNoticeUseCase
import com.gradation.lift.model.model.notification.Notice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * [NoticeViewModel]
 * @property noticeUiState 모든 공지사항 리스트
 * @property selectedNotice 현재 선택된 공지사항 공지사항 상세화면에서 해당 공지를 볼 수 있음
 * @since 2023-10-03 17:18:32
 */
@HiltViewModel
class NoticeViewModel @Inject constructor(
    getNoticeUseCase: GetNoticeUseCase
) : ViewModel() {

    val noticeUiState: StateFlow<NoticeUiState> = getNoticeUseCase().map {
        when (it) {
            is DataState.Fail -> NoticeUiState.Fail(it.message)
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
    val selectedNotice: MutableStateFlow<Notice> = MutableStateFlow(Notice())

    fun updateSelectedNotice(): (Notice) -> Unit = {
        selectedNotice.value = it
    }
}