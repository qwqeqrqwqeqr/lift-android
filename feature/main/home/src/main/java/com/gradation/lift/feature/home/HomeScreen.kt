package com.gradation.lift.feature.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.*
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineGraph
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
        navigateCreateRoutineClick = { navController.navigateToCreateRoutineGraph() },
        onClickWeekDateCard = viewModel::onClickDate
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    today: State<LocalDate>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    userDetailUiState: UserDetailUiState,
    weekDate: List<WeekDate>,
    navigateCreateRoutineClick: () -> Unit,
    onClickWeekDateCard: (LocalDate) -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no1,
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ProfileView(
                modifier = modifier,
                userDetailUiState = userDetailUiState
            )
            Spacer(modifier = modifier.padding(8.dp))
            BadgeView(modifier = modifier)
            Spacer(modifier = modifier.padding(8.dp))

            RoutineView(
                modifier = modifier,
                today,
                weekDateRoutineUiState,
                userDetailUiState,
                weekDate,
                navigateCreateRoutineClick,
                onClickWeekDateCard
            )
        }
    }
}


@Preview
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
            navigateCreateRoutineClick = { },
            onClickWeekDateCard = {}
        )
    }
}