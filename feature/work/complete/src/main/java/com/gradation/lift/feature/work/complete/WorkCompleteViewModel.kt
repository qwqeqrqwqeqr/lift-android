package com.gradation.lift.feature.work.complete

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.history.CreateHistoryUseCase
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import javax.inject.Inject
import java.time.LocalDateTime as CurrentTime

@HiltViewModel
class WorkCompleteViewModel @Inject constructor(
    private val createHistoryUseCase: CreateHistoryUseCase
) : ViewModel() {


    val score = MutableStateFlow(5)
    val comment = MutableStateFlow("")
    val navigateCondition = MutableStateFlow(false)


    fun updateScore(): (Int) -> Unit = {
        score.value = it
    }

    fun updateComment(): (String) -> Unit = {
        comment.value = it
    }

    private fun updateNavigateCondition(value: Boolean) {
        navigateCondition.value = value
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createWorkHistory(
        comment: String?,
        score: Int,
        workTime: LocalTime,
        restTime: LocalTime,
        totalTime: LocalTime,
        historyTimeStamp: LocalDateTime = CurrentTime.now().toKotlinLocalDateTime(),
        historyRoutine: List<CreateHistoryRoutine>
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
                    is DataState.Fail -> updateNavigateCondition(value = false)
                    is DataState.Success -> {
                        updateNavigateCondition(value = it.data)
                    }
                }
            }
        }
    }


}