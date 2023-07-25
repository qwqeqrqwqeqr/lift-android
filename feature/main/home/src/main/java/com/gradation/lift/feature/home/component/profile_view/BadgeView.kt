package com.gradation.lift.feature.home.component.profile_view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
internal fun BadgeView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(96.dp)
                        )
                        .size(56.dp),
                    contentAlignment = Alignment.Center
                ){
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(LiftIcon.Plus),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no6,
                            modifier=modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "뱃지추가",
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9,
                )

            }

        }
        Spacer(modifier = modifier.padding(8.dp))

        LiftOutlineButton(
            modifier = modifier
                .height(32.dp),
            contentPadding = PaddingValues(
                start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
            ),
            onClick = {},
        ) {
            Text(
                text = "전체보기",
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
            )
        }
    }
}


@Preview
@Composable
@SuppressLint("UnrememberedMutableState")
internal fun HomeScreenPreview() {
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
                    profilePicture = "https://plchldr.co/i/245x155?bg=EB6361",
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
            onClickStartWorkWithRoutineSetId = {},
            onClickWeekDateCard = {},
            onClickAddRoutine = {},
            onClickModifyRoutine = {},
            onClickAlarm = {},
            onClickType = {},
            scrollState = rememberScrollState()
        )
    }
}