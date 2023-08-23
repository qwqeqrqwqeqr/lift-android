package com.gradation.lift.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.*
import com.gradation.lift.feature.home.component.profile_view.ProfileView
import com.gradation.lift.feature.home.component.weekdate_routine_view.WeekDateRoutineView
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import com.gradation.lift.feature.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.data.state.WeekDateRoutineUiState
import com.gradation.lift.model.model.common.UnitOfWeight
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
import com.gradation.lift.model.utils.ModelDataGenerator
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomeRoute(
    navController: NavController,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val today: LocalDate by viewModel.today.collectAsStateWithLifecycle()
    val weekDateSelectionList: List<WeekDateSelection> by viewModel.weekDateSelectionList.collectAsStateWithLifecycle()

    val weekDateRoutineUiState: WeekDateRoutineUiState by viewModel.weekDateRoutineUiState.collectAsStateWithLifecycle()
    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()

    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate()
    val updateRoutineSetIdKey: (NavController, Int) -> Unit = viewModel.updateRoutineSetIdKey()

    val scrollState = rememberScrollState()


    HomeScreen(
        modifier,
        navController,
        today,
        weekDateSelectionList,
        weekDateRoutineUiState,
        userDetailUiState,
        updateSelectedDate,
        updateRoutineSetIdKey,
        navigateMainGraphToCreateRoutineGraph,
        navigateMainGraphToWorkGraph,
        scrollState
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    today: LocalDate,
    weekDateSelectionList: List<WeekDateSelection>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    userDetailUiState: UserDetailUiState,
    updateSelectedDate: (LocalDate) -> Unit,
    updateRoutineSetIdKey: (NavController, Int) -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    scrollState: ScrollState,
) {
    Scaffold(
        modifier = modifier
    ) {
        Surface(
            modifier = modifier.padding(it),
            color = LiftTheme.colorScheme.no17
        ) {
            Column(
                modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column {
                    ProfileView(
                        modifier = modifier,
                        userDetailUiState = userDetailUiState,
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    WeekDateRoutineView(
                        modifier,
                        navController,
                        today,
                        weekDateSelectionList,
                        weekDateRoutineUiState,
                        updateSelectedDate,
                        updateRoutineSetIdKey,
                        navigateMainGraphToCreateRoutineGraph,
                        navigateMainGraphToWorkGraph
                    )

                    StartWorkView(modifier, navigateMainGraphToWorkGraph)
                }
            }
        }
    }
}


@Preview
@Composable
internal fun HomeScreenPreview() {
    LiftMaterialTheme {
        HomeScreen(
            modifier = Modifier,
            navController = rememberNavController(),
            today = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            weekDateSelectionList = listOf(
                WeekDateSelection(
                    day = "7",
                    weekday = Weekday.Monday(),
                    selected = false
                ),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(
                    day = "13",
                    weekday = Weekday.Sunday(),
                    selected = true
                ),
            ),
            weekDateRoutineUiState = WeekDateRoutineUiState.Success(
                weekDateRoutine = listOf(
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel1.copy(id = 1),
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel2.copy(id = 2),
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel1.copy(id = 3),
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel2.copy(id = 4),
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel1.copy(id = 5),
                    ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModel2.copy(id = 6),
                )
            ),
            userDetailUiState = UserDetailUiState.Success(
                UserDetail(
                    name = "리프트",
                    weight = 90f,
                    height = 180f,
                    gender = Gender.Male(),
                    profilePicture = FAKE_URL_DATA,
                    unitOfWeight = UnitOfWeight.Kg()
                )
            ),
            updateSelectedDate = {},
            updateRoutineSetIdKey = { _, _ -> },
            navigateMainGraphToCreateRoutineGraph = { },
            navigateMainGraphToWorkGraph = { },
            scrollState = rememberScrollState(),
        )
    }
}