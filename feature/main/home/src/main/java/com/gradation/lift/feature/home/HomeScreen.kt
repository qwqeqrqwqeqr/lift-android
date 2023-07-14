package com.gradation.lift.feature.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val weekDateRoutine: WeekDateRoutineUiState by viewModel.weekDateRoutine.collectAsStateWithLifecycle()
    val weekDate: List<WeekDate> by viewModel.weekDate.collectAsStateWithLifecycle()
    val currentDate = viewModel.currentDate.collectAsState()


    HomeScreen(
        modifier = modifier,
        currentDate = currentDate,
        weekDateRoutine = weekDateRoutine,
        weekDate = weekDate,
        navigateCreateRoutineClick = { navController.navigateToCreateRoutineGraph() },
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
        color = LiftTheme.colorScheme.no1,
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 24.dp,
                            bottomStart = 24.dp
                        )

                    )
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고",
                    modifier.size(72.dp),
                )
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    //TODO Profile 이미지 끌고오기
                    Box(
                        modifier = modifier
                            .background(
                                color = LiftTheme.colorScheme.no1,
                                shape = RoundedCornerShape(96.dp)
                            )
                            .size(72.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "사진",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no11
                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))
                    Column(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier,
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                text = "리프트",
                                style = LiftTheme.typography.no1,
                                color = LiftTheme.colorScheme.no11,
                            )
                            Text(
                                text = "180cm/50kg",
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no11
                            )
                        }
                    }

                }
            }

        }
    }
}

@Preview
@Composable
@SuppressLint("UnrememberedMutableState")
fun HomeScreenPreview() {
    LiftMaterialTheme {
        HomeScreen(
            currentDate = mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())),
            weekDateRoutine = WeekDateRoutineUiState.Empty,
            weekDate = emptyList(),
            navigateCreateRoutineClick = { },
            onClickWeekDateCard = {}
        )
    }
}