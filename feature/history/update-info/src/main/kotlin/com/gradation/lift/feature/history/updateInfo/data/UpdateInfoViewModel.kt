package com.gradation.lift.feature.history.updateInfo.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_COMMENT_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.History.HISTORY_SCORE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [UpdateInfoViewModel]
 * @since 2024-01-26 16:20:01
 */
@HiltViewModel
class UpdateInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val defaultComment: String = savedStateHandle.get<String>(HISTORY_COMMENT_KEY) ?: ""
    val defaultScore: Int = savedStateHandle.get<Int>(HISTORY_SCORE_KEY) ?: 0


}