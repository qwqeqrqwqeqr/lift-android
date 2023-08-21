package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.create_routine.routine_set.component.routine_list_view.EmptyRoutineListView
import com.gradation.lift.feature.create_routine.routine_set.component.routine_list_view.RoutineListView
import com.gradation.lift.model.model.routine.CreateRoutine


@Composable
fun RoutineSetRoutineView(
    modifier: Modifier = Modifier,
    routineSetRoutine: List<CreateRoutine>,
    removeRoutine: (CreateRoutine) -> Unit,
    navigateRoutineSetToFindWorkCategory: () -> Unit,
) {

    if (routineSetRoutine.isEmpty()) {
        EmptyRoutineListView(
            modifier, navigateRoutineSetToFindWorkCategory
        )
    } else {
        RoutineListView(
            modifier, routineSetRoutine, removeRoutine, navigateRoutineSetToFindWorkCategory
        )
    }


}