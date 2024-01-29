package com.gradation.lift.feature.work.completedetail.data.state

import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.historyCommentValidator
import com.gradation.lift.feature.work.completedetail.data.event.HistoryInfoEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


/**
 * [HistoryInfoState]
 * @property score 운동 기록 자체 평가 점수
 * @property comment 운동 기록 메모
 * @property commentValidator 운동 메모 유효성 검증
 * @since 2023-12-12 17:20:44
 */
internal data class HistoryInfoState(
    private val viewModelScope: CoroutineScope,
) {
    val score: MutableStateFlow<Int> = MutableStateFlow(3)
    val comment: MutableStateFlow<String> = MutableStateFlow("")

    var commentValidator: StateFlow<Validator> =
        comment.map { it ->
            if (it.isBlank()) {
                Validator(true, "")
            } else if (!historyCommentValidator(it)) {
                Validator(false, "0 - 20자 사이의 글자로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    val updateComment: (String) -> Unit = { onHistoryInfoEvent(HistoryInfoEvent.UpdateComment(it)) }
    val updateScore: (Int) -> Unit = { onHistoryInfoEvent(HistoryInfoEvent.UpdateScore(it)) }

    private fun onHistoryInfoEvent(historyInfoEvent: HistoryInfoEvent) {
        when (historyInfoEvent) {
            is HistoryInfoEvent.UpdateComment -> comment.value = historyInfoEvent.comment
            is HistoryInfoEvent.UpdateScore -> score.value = historyInfoEvent.score
        }
    }
}