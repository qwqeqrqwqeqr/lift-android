package com.gradation.lift.feature.updateRoutine.changeOrder.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ChangeOrderRoute(
    modifier: Modifier = Modifier,
    navigateChangeOrderToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {





    BackHandler(onBack = navigateChangeOrderToRoutineSetInUpdateRoutineGraph)
}