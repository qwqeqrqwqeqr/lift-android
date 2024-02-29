package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun FailRoutineListView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
) {
    RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)
}