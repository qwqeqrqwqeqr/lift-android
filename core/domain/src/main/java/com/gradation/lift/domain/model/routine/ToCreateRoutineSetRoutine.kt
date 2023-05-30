package com.gradation.lift.domain.model.routine

import com.gradation.lift.domain.model.common.RepeatIntervalType
import com.gradation.lift.domain.model.work.WorkSet

data class CreateRoutineSetRoutine(
    val userId: String,
    val shortDescription: String,
    val longDescription: String,
    val repeatIntervalType: RepeatIntervalType,
    val routine : List<CreateRoutineSetRoutineDetail>
){

}

data class CreateRoutineSetRoutineDetail(
    val workCategoryId: Int,
    val workSet: List<WorkSet>
)


