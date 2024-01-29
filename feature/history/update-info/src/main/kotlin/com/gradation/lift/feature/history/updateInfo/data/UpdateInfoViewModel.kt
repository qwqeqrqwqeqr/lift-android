package com.gradation.lift.feature.history.updateInfo.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.history.UpdateHistoryInfoUseCase
import com.gradation.lift.feature.history.updateInfo.data.state.HistoryInfoState
import com.gradation.lift.feature.history.updateInfo.data.state.UpdateHistoryInfoState
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_COMMENT_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_DATE_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_ID_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_SCORE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UpdateInfoViewModel]
 * @since 2024-01-26 16:20:01
 */
@HiltViewModel
class UpdateInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateHistoryInfoUseCase: UpdateHistoryInfoUseCase,
) : ViewModel() {

    private val historyId: Int = savedStateHandle.get<Int>(HISTORY_ID_KEY) ?: 0
    val date: String = savedStateHandle.get<String>(HISTORY_DATE_KEY) ?: ""

    val defaultComment: String = savedStateHandle.get<String>(HISTORY_COMMENT_KEY) ?: ""
    val defaultScore: Int = savedStateHandle.get<Int>(HISTORY_SCORE_KEY) ?: 0

    internal val historyInfoState = HistoryInfoState(
        viewModelScope = viewModelScope,
        defaultComment, defaultScore
    )

    val updateHistoryInfoState: MutableStateFlow<UpdateHistoryInfoState> =
        MutableStateFlow(UpdateHistoryInfoState.None)
    val updateUpdateHistoryInfoState: (UpdateHistoryInfoState) -> Unit = {
        updateHistoryInfoState.value = it
    }

    val updateHistoryInfo: () -> Unit = {
        viewModelScope.launch {
            updateHistoryInfoUseCase(
                UpdateHistoryInfo(
                    historyId = historyId,
                    score = historyInfoState.score.value,
                    comment = historyInfoState.comment.value
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> updateHistoryInfoState.value =
                        UpdateHistoryInfoState.Fail(it.message)

                    is DataState.Success -> updateHistoryInfoState.value =
                        UpdateHistoryInfoState.Success
                }
            }
        }
    }

}