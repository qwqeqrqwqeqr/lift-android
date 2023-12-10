package com.gradation.lift.feature.createRoutine.routineSet.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.createRoutine.routineSet.component.routine_list_view.EmptyRoutineListView
import com.gradation.lift.feature.createRoutine.routineSet.component.routine_list_view.RoutineListView
import com.gradation.lift.model.model.routine.CreateRoutine


@Composable
fun RoutineSetRoutineView(
    modifier: Modifier = Modifier,
    routineSetRoutine: List<CreateRoutine>,
    removeRoutine: (CreateRoutine) -> Unit,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
) {

    if (routineSetRoutine.isEmpty()) {
        EmptyRoutineListView(
            modifier, navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
        )
    } else {
        RoutineListView(
            modifier, routineSetRoutine, removeRoutine, navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
        )
    }


}