package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.network.dto.history.CreateHistoryRequestDto
import com.gradation.lift.network.dto.history.CreateHistoryRoutineDto


fun CreateHistory.toDto() : CreateHistoryRequestDto =
    CreateHistoryRequestDto(
        comment = this.comment,
        score = this.score,
        workTime = this.workTime.toSecondOfDay(),
        restTime = this.restTime.toSecondOfDay(),
        totalTime = this.totalTime.toSecondOfDay(),
        historyTimeStamp  = this.historyTimeStamp.toString(),
        historyRoutine = this.historyRoutine.map { historyRoutine ->
            CreateHistoryRoutineDto(
                workCategory = historyRoutine.workCategory,
                workWeightList = historyRoutine.workSetList.map { it.weight },
                workRepetitionList = historyRoutine.workSetList.map { it.repetition }
            )
        }
    )