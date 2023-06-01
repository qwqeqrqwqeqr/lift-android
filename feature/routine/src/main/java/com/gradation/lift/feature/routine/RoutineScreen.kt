package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine.component.RoutineBody
import com.gradation.lift.feature.routine.component.RoutineHeader
import com.gradation.lift.feature.routine.viewmodel.RoutineViewModel
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun RoutineRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineViewModel = hiltViewModel()
) {
    val weekDateRoutineUiState : WeekDateRoutineUiState by viewModel.weekDateRoutineUiState.collectAsStateWithLifecycle()
    RoutineScreen(
        modifier = modifier,
        currentDate = viewModel.currentDate,
        weekDateRoutineUiState = weekDateRoutineUiState
    )
}


@Composable
internal fun RoutineScreen(
    modifier: Modifier = Modifier,
    currentDate: String,
    weekDateRoutineUiState: WeekDateRoutineUiState
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            RoutineHeader(
                onClick = {},
                modifier = modifier
            )
            Spacer(modifier = modifier.height(16.dp))
            RoutineBody(
                modifier = modifier,
                currentDate = currentDate,
                weekDateRoutineUiState = weekDateRoutineUiState
            )

        }
    }
}

