package com.gradation.lift.feature.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftHomeTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.*
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
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
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val scrollState: ScrollState = rememberScrollState()
    val weekDateRoutineUiState: WeekDateRoutineUiState by viewModel.weekDateRoutine.collectAsStateWithLifecycle()
    val userDetailUiState: UserDetailUiState by viewModel.userDetail.collectAsStateWithLifecycle()
    val weekDate: List<WeekDate> by viewModel.weekDate.collectAsStateWithLifecycle()
    val today = viewModel.today.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        today = today,
        userDetailUiState = userDetailUiState,
        weekDateRoutineUiState = weekDateRoutineUiState,
        weekDate = weekDate,
        onClickCreateRoutine = { navController.navigateToCreateRoutineGraph() },
        onClickStartWork = {},
        onClickWeekDateCard = viewModel::onClickDate,
        onClickAddRoutine = {},
        onClickUpdateRoutine = {},
        onClickAlarm={},
        onClickType={},
        scrollState = scrollState
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    today: State<LocalDate>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    userDetailUiState: UserDetailUiState,
    weekDate: List<WeekDate>,
    onClickCreateRoutine: () -> Unit,
    onClickStartWork: () -> Unit,
    onClickWeekDateCard: (LocalDate) -> Unit,
    onClickAddRoutine: () -> Unit,
    onClickUpdateRoutine: () -> Unit,
    onClickAlarm : () -> Unit,
    onClickType : () -> Unit,
    scrollState: ScrollState,
) {
    Scaffold(
        topBar = {
            LiftHomeTopBar()
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    )
            ) {
                Text(
                    text = "운동시작하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
        ) {

            ProfileView(
                modifier = modifier,
                userDetailUiState = userDetailUiState,
                onClickAlarm=onClickAlarm,
                onClickType=onClickType,
            )
            Spacer(modifier = modifier.padding(8.dp))
            BadgeView(modifier = modifier)
            Spacer(modifier = modifier.padding(8.dp))

            RoutineView(
                modifier = modifier,
                today = today,
                weekDateRoutineUiState = weekDateRoutineUiState,
                weekDate = weekDate,
                onClickCreateRoutine = onClickCreateRoutine,
                onClickWeekDateCard = onClickWeekDateCard,
                onClickAddRoutine = onClickAddRoutine,
                onClickUpdateRoutine = onClickUpdateRoutine
            )
            Spacer(modifier = modifier.padding(72.dp))
        }
    }
}


@DevicePreview
@Composable
@SuppressLint("UnrememberedMutableState")
fun HomeScreenPreview() {
    LiftMaterialTheme {
        HomeScreen(
            today = mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())),
            weekDateRoutineUiState = WeekDateRoutineUiState.Empty,
            userDetailUiState = UserDetailUiState.Success(
                UserDetail(
                    name = "리프트",
                    weight = 90f,
                    height = 180f,
                    gender = Gender.Male(),
                    unitOfWeight = UnitOfWeight.Kg()
                )
            ),
            weekDate = listOf(
                WeekDate(),
                WeekDate(),
                WeekDate(),
                WeekDate(),
                WeekDate(),
                WeekDate(),
                WeekDate(selected = true),
            ),
            onClickCreateRoutine = { },
            onClickStartWork = {},
            onClickWeekDateCard = {},
            onClickAddRoutine = {},
            onClickUpdateRoutine = {},
            onClickAlarm={},
            onClickType={},
            scrollState = rememberScrollState()
        )
    }
}