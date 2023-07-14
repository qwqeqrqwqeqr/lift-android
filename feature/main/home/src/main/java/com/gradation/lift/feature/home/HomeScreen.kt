package com.gradation.lift.feature.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.CreateRoutineView
import com.gradation.lift.feature.home.component.RoutineBody
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineGraph
import com.gradation.lift.ui.DevicePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val weekDateRoutine : WeekDateRoutineUiState by viewModel.weekDateRoutine.collectAsStateWithLifecycle()
    val weekDate : List<WeekDate> by viewModel.weekDate.collectAsStateWithLifecycle()
    val currentDate  = viewModel.currentDate.collectAsState()


    HomeScreen(
        modifier = modifier,
        currentDate = currentDate,
        weekDateRoutine = weekDateRoutine,
        weekDate = weekDate,
        navigateCreateRoutineClick = {navController.navigateToCreateRoutineGraph()},
        onClickWeekDateCard = viewModel::onClickDate
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    currentDate: State<LocalDate>,
    weekDateRoutine: WeekDateRoutineUiState,
    weekDate: List<WeekDate>,
    navigateCreateRoutineClick: () -> Unit,
    onClickWeekDateCard: (LocalDate) -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5,
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@DevicePreview
@Composable
fun HomeScreenPreview(){
    LiftMaterialTheme {
        HomeScreen(
            currentDate = mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())) ,
            weekDateRoutine = WeekDateRoutineUiState.Empty ,
            weekDate = emptyList(),
            navigateCreateRoutineClick = {  },
            onClickWeekDateCard = {}
        )
    }
}