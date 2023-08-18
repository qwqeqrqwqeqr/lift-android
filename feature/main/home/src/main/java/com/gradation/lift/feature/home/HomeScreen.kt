package com.gradation.lift.feature.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.*
import com.gradation.lift.feature.home.component.profile_view.ProfileView
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import com.gradation.lift.feature.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.data.state.WeekDateRoutineUiState
import com.gradation.lift.model.model.common.UnitOfWeight
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_URL_DATA
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
    updateKey: (NavController, Int) -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    scrollState: ScrollState,
) {
    Scaffold {
        Surface(
            modifier = modifier.padding(it),
            color = LiftTheme.colorScheme.no17
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = modifier.verticalScroll(scrollState)
                ) {

                    ProfileView(
                        modifier = modifier,
                        userDetailUiState = userDetailUiState,
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    RoutineView(
                        modifier = modifier,
                        today = today,
                        weekDateRoutineUiState = weekDateRoutineUiState,
                        weekDate = weekDate,
                        onClickCreateRoutine = onClickCreateRoutine,
                        onClickWeekDateCard = onClickWeekDateCard,
                        onClickStartWorkWithRoutineSetId = onClickStartWorkWithRoutineSetId,
                        onClickAddRoutine = onClickAddRoutine,
                        onClickUpdateRoutine = onClickModifyRoutine
                    )
                }
                StartWorkView(modifier, navigateMainGraphToWorkGraph)
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
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(),
                WeekDateSelection(selected = true),
            ),
            weekDateRoutineUiState = WeekDateRoutineUiState.Empty,
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
            updateKey = { _, _ -> },
            navigateMainGraphToCreateRoutineGraph = { },
            navigateMainGraphToWorkGraph = { },
            scrollState = rememberScrollState(),
        )
    }
}