package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine


fun History.toEntity() = HistoryEntity(
    historyId, comment, score,workTime, restTime, totalTime, historyTimeStamp
)


fun HistoryRoutine.toEntity() = HistoryRoutineEntity(
    id,historyId,workCategory.toEntity(),workSetList
)