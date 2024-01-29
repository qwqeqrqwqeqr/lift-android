package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.routine.UpdateRoutineSetCount
import com.gradation.lift.network.dto.routine.UpdateRoutineSetCountRequestDto


fun UpdateRoutineSetCount.toDto(): UpdateRoutineSetCountRequestDto =
    UpdateRoutineSetCountRequestDto(routineSetIdList = this.routineSetIdList)