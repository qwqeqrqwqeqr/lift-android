package com.gradation.lift.feature.home.component.routine_list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.HomeScreen
import com.gradation.lift.feature.home.data.UserDetailUiState
import com.gradation.lift.feature.home.data.WeekDate
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.test.data.TestModelDataGenerator
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun RoutineListView(
    modifier: Modifier = Modifier,
    onClickUpdateRoutine: () -> Unit,
    onClickAddRoutine: () -> Unit,
    onClickStartWorkWithRoutineSetId: (Int) -> Unit,
    routineSetRoutineList: List<RoutineSetRoutine>,
) {
    Column {
        Spacer(modifier = modifier.padding(7.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = LiftIcon.Plus),
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
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = modifier.padding(5.dp))
        Column(

            verticalArrangement = Arrangement.spacedBy(
                4.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            routineSetRoutineList.forEach { routineSetRoutine ->
                Box(
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(65.dp)
                        .clickable {
                            onClickStartWorkWithRoutineSetId(routineSetRoutine.id)
                        },
                    contentAlignment = Alignment.CenterStart
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(38.dp)
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = routineSetRoutine.name,
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no9,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = routineSetRoutine.description,
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no9,
                                overflow = TextOverflow.Ellipsis
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
fun RoutineListPreview() {
    LiftMaterialTheme {
        HomeScreen(
            today = mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())),
            weekDateRoutineUiState = WeekDateRoutineUiState.Success(weekDateRoutine = TestModelDataGenerator.Routine.routineSetRoutineModelList),
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
            onClickAddRoutine = {},
            onClickUpdateRoutine = {},
            onClickStartWorkWithRoutineSetId = {},
            onClickAlarm = {},
            onClickType = {},
            scrollState = rememberScrollState()
        )
    }
}