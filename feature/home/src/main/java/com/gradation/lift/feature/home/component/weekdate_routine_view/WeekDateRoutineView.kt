package com.gradation.lift.feature.home.component.weekdate_routine_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.NavigationView
import com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view.EmptyRoutineListView
import com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view.LoadingRoutineListView
import com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view.RoutineListView
import com.gradation.lift.feature.home.component.weekdate_routine_view.weekdate_view.WeekDateView
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import com.gradation.lift.feature.home.data.state.WeekDateRoutineUiState
import kotlinx.datetime.LocalDate


@Composable
internal fun WeekDateRoutineView(
    modifier: Modifier = Modifier,
    navController: NavController,
    today: LocalDate,
    weekDateSelectionList: List<WeekDateSelection>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    updateSelectedDate: (LocalDate) -> Unit,
    updateRoutineSetIdKey: (NavController, Int) -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .padding(16.dp)
    ) {

        WeekDateView(modifier, today, weekDateSelectionList, updateSelectedDate)


            when (weekDateRoutineUiState) {
                WeekDateRoutineUiState.Empty -> {
                    EmptyRoutineListView(
                        modifier,
                        navigateMainGraphToCreateRoutineGraph
                    )
                }
                is WeekDateRoutineUiState.Fail -> {

                }
                WeekDateRoutineUiState.Loading -> {
                    LoadingRoutineListView(modifier)

                }
                is WeekDateRoutineUiState.Success -> {
                    Column {
                        RoutineListView(
                            modifier.fillMaxHeight(),
                            navController,
                            routineSetRoutineList = weekDateRoutineUiState.weekDateRoutine,
                            updateRoutineSetIdKey,
                            navigateMainGraphToCreateRoutineGraph,
                            navigateMainGraphToWorkGraph
                        )
                        Spacer(modifier = modifier.padding(128.dp))
                        NavigationView(modifier.fillMaxHeight(), navigateMainGraphToWorkGraph)
                    }
                }
            }
        }

}



