package com.gradation.lift.feature.updateRoutine.common.data.state

import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine

internal sealed interface CurrentRoutineSetRoutineEvent {
    data class UpdateRoutineSetName(val name: String) : CurrentRoutineSetRoutineEvent
    data object ClearRoutineSetName : CurrentRoutineSetRoutineEvent
    data class UpdateRoutineSetDescription(val description: String) : CurrentRoutineSetRoutineEvent
    data object ClearRoutineSetDescription : CurrentRoutineSetRoutineEvent
    data class UpdateRoutineSetWeekday(val weekday: Set<Weekday>) : CurrentRoutineSetRoutineEvent
    data class UpdateRoutineSetProfilePicture(val profilePicture: String) : CurrentRoutineSetRoutineEvent
    data class UpdateRoutineSetLabel(val label: Set<Label>) : CurrentRoutineSetRoutineEvent
    data class RemoveRoutine(val routine: Routine) : CurrentRoutineSetRoutineEvent
    data class AppendRoutine(val routine: Routine) : CurrentRoutineSetRoutineEvent
}