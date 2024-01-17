package com.gradation.lift.feature.notice.noticeDetail.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.notice.GetNoticeByIdUseCase
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotificationNoticeDetailViewModel @Inject constructor(
    getNoticeByIdUseCase: GetNoticeByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val noticeId = savedStateHandle.get<Int>(SavedStateHandleKey.Notice.NOTICE_ID_KEY)

    val noticeUiState: StateFlow<NoticeUiState> = (noticeId?.let {
        getNoticeByIdUseCase(noticeId).map {
            when (it) {
                is DataState.Success ->
                    NoticeUiState.Success(it.data)

                is DataState.Fail -> NoticeUiState.Fail(it.message)
            }
        }
    } ?: flowOf(NoticeUiState.Loading)).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NoticeUiState.Loading
    )
}