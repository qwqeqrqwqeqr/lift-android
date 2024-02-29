package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.routine.UpdateUsedRoutineSet
import com.gradation.lift.network.dto.routine.UpdateUsedRoutineSetRequestDto


fun UpdateUsedRoutineSet.toDto(): UpdateUsedRoutineSetRequestDto =
    UpdateUsedRoutineSetRequestDto(
        routineSetIdList = this.routineSetIdList,
        usedTimeStamp = this.usedTimeStamp.toString(),
    )