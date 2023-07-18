package com.gradation.lift.feature.home.component.profile_view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.LiftIconButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.HomeScreen
import com.gradation.lift.feature.home.component.profile_view.BadgeView
import com.gradation.lift.feature.home.data.UserDetailUiState
import com.gradation.lift.feature.home.data.WeekDate
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.ui.DevicePreview
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
internal fun ProfileView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    onClickAlarm : () -> Unit,
    onClickType : () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
   
            .padding(20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Box(
                modifier = modifier
                    .background(
                        color = LiftTheme.colorScheme.no1,
                        shape = RoundedCornerShape(96.dp)
                    )
                    .size(72.dp)
            )
            Spacer(modifier = modifier.padding(8.dp))
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                when (userDetailUiState) {
                    is UserDetailUiState.Fail -> {
                        Text(
                            text = "",
                            style = LiftTheme.typography.no1,
                            color = LiftTheme.colorScheme.no11,
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Text(
                            text = "",
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no11
                        )
                    }
                    UserDetailUiState.Loading -> Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Box(
                            modifier
                                .background(
                                    SkeletonBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .width(54.dp)
                                .height(24.dp),
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Box(
                            modifier
                                .background(
                                    SkeletonBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .width(96.dp)
                                .height(24.dp),

                            )
                    }
                    is UserDetailUiState.Success -> Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = userDetailUiState.userDetail.name,
                            style = LiftTheme.typography.no1,
                            color = LiftTheme.colorScheme.no11,
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Text(
                            text = "${userDetailUiState.userDetail.height}cm/${userDetailUiState.userDetail.weight}kg",
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no11
                        )
                    }
                }
                Row(
                    modifier =modifier.clickable(onClick = onClickType),
                    verticalAlignment= Alignment.CenterVertically
                ) {
                    Text(
                        text = "운동 초심자",
                        style = LiftTheme.typography.no4,
                        color = LiftTheme.colorScheme.no11
                    )
                    Spacer(modifier = modifier.padding(1.dp))
                    Icon(
                        painter = painterResource(LiftIcon.ChevronRightSharp),
                        contentDescription = "",
                    )
                }
            }
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                LiftIconButton(onClick = onClickAlarm) {
                    Icon(
                        painter = painterResource(LiftIcon.Bell),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
        Spacer(modifier = modifier.padding(16.dp))
        BadgeView(modifier = modifier)
    }

}



