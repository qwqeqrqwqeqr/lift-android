package com.gradation.lift.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.home.data.HomeViewModel
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.feature.home.data.WeekDateUiState
import com.gradation.lift.feature.home.component.RoutineBody
import com.gradation.lift.feature.home.component.RoutineHeader
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineGraph
import kotlinx.datetime.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val weekDateRoutineUiState : WeekDateRoutineUiState by viewModel.weekDateRoutineUiState.collectAsStateWithLifecycle()
    val weekDateUiState  : WeekDateUiState by viewModel.weekDateUiState.collectAsStateWithLifecycle()
    val currentDate  = viewModel.currentDate


    HomeScreen(
        modifier = modifier,
        currentDate = currentDate,
        weekDateRoutineUiState = weekDateRoutineUiState,
        weekDateUiState= weekDateUiState,
        navigateCreateRoutineClick = {navController.navigateToCreateRoutineGraph()},
        weekCardClick = viewModel::onClickDate
    )
}


@Composable
internal fun HomeScreen(
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

