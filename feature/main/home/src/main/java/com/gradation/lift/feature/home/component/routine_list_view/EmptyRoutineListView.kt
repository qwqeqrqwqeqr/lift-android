package com.gradation.lift.feature.home.component.routine_list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
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
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun EmptyRoutineListView(
    modifier: Modifier = Modifier,
    onClickCreateRoutine: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = modifier.padding(36.dp))
        Image(
            painter = painterResource(id = com.gradation.lift.designsystem.R.drawable.open_box),
            contentDescription = "",
            modifier = modifier
                .width(72.dp)
                .height(54.dp)
        )
        Spacer(modifier = modifier.padding(4.dp))
        Text(
            text = "아직 루틴리스트가 없네요...",
            style = LiftTheme.typography.no4,
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.padding(8.dp))
        LiftOutlineButton(
            modifier = modifier.fillMaxWidth(),
            onClick = onClickCreateRoutine,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "루틴 리스트 만들기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
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
        Spacer(modifier = modifier.padding(24.dp))

    }
}

@Preview
@Composable
@SuppressLint("UnrememberedMutableState")
fun EmptyRoutineListPreview() {
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
                    profilePicture = FAKE_STRING_DATA,
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