package com.gradation.lift.feature.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.HomeScreen
import com.gradation.lift.feature.home.component.routine_list_view.LoadingRoutineListView
import com.gradation.lift.feature.home.data.UserDetailUiState
import com.gradation.lift.feature.home.data.WeekDate
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.test.data.TestModelDataGenerator
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
fun RoutineView(
    modifier: Modifier = Modifier,
    today: State<LocalDate>,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    weekDate: List<WeekDate>,
    onClickWeekDateCard: (LocalDate) -> Unit,
    onClickCreateRoutine: () -> Unit,
    onClickStartWork: () -> Unit,
    onClickAddRoutine: () -> Unit,
    onClickUpdateRoutine : () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(24.dp)
            )
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "내 루틴 리스트",
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no9,
            )
            Text(
                text = buildAnnotatedString {
                    append("오늘은 ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                    ) {
                        append("${today.value.monthNumber}월 ${today.value.dayOfMonth}일")
                    }
                    append("이에요!")
                },
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no9,
            )
            Spacer(modifier = modifier.padding(8.dp))
            WeekDateView(weekDate = weekDate, onClickWeekDateCard = onClickWeekDateCard)

            when (weekDateRoutineUiState) {
                WeekDateRoutineUiState.Empty -> {
                    EmptyRoutineListView(
                        modifier = modifier,
                        onClickCreateRoutine = onClickCreateRoutine
                    )
                }
                is WeekDateRoutineUiState.Fail -> {

                }
                WeekDateRoutineUiState.Loading -> {
                    LoadingRoutineListView(modifier = modifier)
                }
                is WeekDateRoutineUiState.Success -> {
                    Column {
                            Spacer(modifier = modifier.padding(7.dp))
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.End),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                LiftOutlineButton(
                                    modifier = modifier
                                        .height(32.dp),
                                    contentPadding = PaddingValues(
                                        start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                                    ),
                                    onClick = onClickAddRoutine,
                                ) {
                                    Text(
                                        text = "추가",
                                        style = LiftTheme.typography.no5,
                                        color = LiftTheme.colorScheme.no4,
                                    )
                                    Spacer(modifier = modifier.padding(1.dp))
                                    Icon(
                                        painter =  painterResource(id = LiftIcon.Plus),
                                        contentDescription = null,
                                    )
                                }

                                LiftOutlineButton(
                                    modifier = modifier
                                        .height(32.dp),
                                    contentPadding = PaddingValues(
                                        start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                                    ),
                                    onClick = onClickUpdateRoutine,
                                ) {
                                    Text(
                                        text = "수정",
                                        style = LiftTheme.typography.no5,
                                        color = LiftTheme.colorScheme.no4,
                                    )
                                    Spacer(modifier = modifier.padding(1.dp))
                                    Icon(
                                        painter =  painterResource(id = LiftIcon.ChevronRight),
                                        contentDescription = null,
                                    )
                                }
                            }
                    }
                }
            }


        }
        Column {
            Spacer(
                modifier = modifier
                    .padding(16.dp)
                    .background(
                        color = LiftTheme.colorScheme.no5
                    )
            )
            LiftButton(
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork
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
                )
            }
            Spacer(
                modifier = modifier
                    .padding(48.dp)
                    .background(
                        color = LiftTheme.colorScheme.no5
                    )
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
            weekDateRoutineUiState = WeekDateRoutineUiState.Success(
                TestModelDataGenerator.Routine.routineSetRoutineModelList
            ),
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
            onClickCreateRoutine = {},
            onClickWeekDateCard = {},
            onClickStartWork = {},
            onClickAddRoutine={},
            onClickUpdateRoutine={},
            scrollState = rememberScrollState()
        )
    }
}