package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.routine.component.RoutineBody
import com.gradation.lift.feature.routine.component.RoutineHeader
import com.gradation.lift.feature.routine.viewmodel.RoutineViewModel
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import com.gradation.lift.feature.routine.viewmodel.WeekDateUiState
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineGraph
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoutineRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RoutineViewModel = hiltViewModel()
) {
    val weekDateRoutineUiState : WeekDateRoutineUiState by viewModel.weekDateRoutineUiState.collectAsStateWithLifecycle()
    val weekDateUiState  : WeekDateUiState by viewModel.weekDateUiState.collectAsStateWithLifecycle()
    val currentDate  = viewModel.currentDate
    RoutineScreen(
        modifier = modifier,
        currentDate = currentDate,
        weekDateRoutineUiState = weekDateRoutineUiState,
        weekDateUiState= weekDateUiState,
        navigateCreateRoutineClick = {navController.navigateToCreateRoutineGraph()},
        weekCardClick = viewModel::onClickDate
    )

}


@Composable
internal fun RoutineScreen(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    weekDateUiState: WeekDateUiState,
    navigateCreateRoutineClick: () -> Unit,
    weekCardClick: (LocalDate) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            RoutineHeader(
                navigateCreateRoutineClick = navigateCreateRoutineClick,
                modifier = modifier
            )
            Spacer(modifier = modifier.height(16.dp))
            RoutineBody(
                modifier = modifier,
                currentDate  =currentDate,
                weekDateRoutineUiState= weekDateRoutineUiState,
                weekDateUiState = weekDateUiState,
                weekCardClick = weekCardClick,
            )
        }
    }
}

