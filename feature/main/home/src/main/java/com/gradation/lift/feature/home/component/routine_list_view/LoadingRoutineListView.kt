package com.gradation.lift.feature.home.component.routine_list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.home.HomeScreen
import com.gradation.lift.feature.home.data.UserDetailUiState
import com.gradation.lift.feature.home.data.WeekDate
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.model.model.common.UnitOfWeight
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
internal fun LoadingRoutineListView(modifier:Modifier=Modifier){
    Column {
        Spacer(modifier = modifier.padding(8.dp))
        Box(
            modifier
                .background(
                    SkeletonBrush(),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(96.dp),
        )
        Spacer(modifier = modifier.padding(8.dp))

        Box(
            modifier
                .background(
                    SkeletonBrush(),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(96.dp),
        )
    }
}

@Preview
@Composable
@SuppressLint("UnrememberedMutableState")
internal fun LoadingRoutineListPreview() {
    LiftMaterialTheme {
        HomeScreen(
            today = mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())),
            weekDateRoutineUiState = WeekDateRoutineUiState.Loading,
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