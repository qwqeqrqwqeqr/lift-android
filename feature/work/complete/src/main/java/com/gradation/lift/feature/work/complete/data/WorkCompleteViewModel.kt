package com.gradation.lift.feature.work.complete.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.historyCommentValidator
import com.gradation.lift.domain.usecase.date.GetNowUseCase
import com.gradation.lift.domain.usecase.history.CreateHistoryUseCase
import com.gradation.lift.feature.work.complete.data.state.CreateWorkHistoryState
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import javax.inject.Inject

/**
 * [WorkCompleteViewModel]
 * @property score 운동 기록 자체 평가 점수
 * @property comment 운동 기록 메모
 * @property commentValidator 운동 메모 유효성 검증
 * @since 2023-08-22 17:47:34
 */
@HiltViewModel
class WorkCompleteViewModel @Inject constructor(
    private val createHistoryUseCase: CreateHistoryUseCase,
    private val getNowUseCase : GetNowUseCase
) : ViewModel() {


    val score: MutableStateFlow<Int> = MutableStateFlow(5)
    val comment: MutableStateFlow<String> = MutableStateFlow("")

    val createWorkHistoryState: MutableStateFlow<CreateWorkHistoryState> =
        MutableStateFlow(CreateWorkHistoryState.None)

    var commentValidator: StateFlow<Validator> =
        comment.map { it ->
            if(it.isBlank()){
                Validator(true, "")
            }
            else if (!historyCommentValidator(it)) {
                Validator(false, "0 - 20자 사이의 글자로 입력해주세요.")
            } else {
                Validator(true, "")
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    fun updateScore(): (Int) -> Unit = {
        score.value = it
    }

    fun updateComment(): (String) -> Unit = {
        comment.value = it
    }

    fun updateCreateWorkHistoryState(): (CreateWorkHistoryState) -> Unit = {
        createWorkHistoryState.value =it
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createWorkHistory(
        comment: String?,
        score: Int,
        workTime: LocalTime,
        restTime: LocalTime,
        totalTime: LocalTime,
        historyTimeStamp: LocalDateTime = getNowUseCase(),
        historyRoutine: List<CreateHistoryRoutine>,
    ): () -> Unit = {
        viewModelScope.launch {
            createHistoryUseCase(
                CreateHistory(
                    comment,
                    score,
                    workTime,
                    restTime,
                    totalTime,
                    historyTimeStamp,
                    historyRoutine
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> createWorkHistoryState.value =
                        CreateWorkHistoryState.Fail(message = it.message)
                    is DataState.Success -> {
                        createWorkHistoryState.value = CreateWorkHistoryState.Success
                    }
                }
            }
        }
    }


}