package com.gradation.lift.feature.workReady.routineSelection.data.model

import com.gradation.lift.model.model.routine.RoutineSetRoutine

data class RecentUsedRoutineSetRoutine(
    val routineSetRoutine: RoutineSetRoutine,
    val recentUsed: Boolean,
)
