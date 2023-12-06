package com.gradation.lift.feature.updateRoutine.routineSet.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.updateRoutine.routineSet.component.routine_list_view.EmptyRoutineListView
import com.gradation.lift.feature.updateRoutine.routineSet.component.routine_list_view.RoutineListView
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine


@Composable
fun RoutineSetRoutineView(
    modifier: Modifier = Modifier,
    selectedRoutineSetRoutine: UpdateRoutineSetRoutine,
    removeRoutine: (UpdateRoutine) -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
) {

    if (selectedRoutineSetRoutine.routine.isEmpty()) {
        EmptyRoutineListView(
            modifier, navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
        )
    } else {
        RoutineListView(
            modifier,
            selectedRoutineSetRoutine.routine,
            removeRoutine,
            navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
        )
    }


}