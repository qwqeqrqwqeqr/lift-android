package com.gradation.lift.feature.home.component.profile_view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.brush.SkeletonLightBrush
import com.gradation.lift.designsystem.component.LiftIconButton
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileDetailView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    onClickAlarm: () -> Unit,
    onClickType: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .background(color = LiftTheme.colorScheme.no4, shape = RoundedCornerShape(size = 16.dp))
            .padding(12.dp, 10.dp)
            .fillMaxWidth()
    ) {
        when (userDetailUiState) {
            is UserDetailUiState.Fail -> {
                Spacer(modifier = modifier.height(72.dp))
            }
            UserDetailUiState.Loading -> {

                Box(
                    modifier
                        .clip(CircleShape)
                        .size(72.dp)
                        .background(SkeletonLightBrush())

                )
                Spacer(modifier = modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .padding(vertical = 10.dp)
                        .fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxSize()
                    ) {
                        Spacer(
                            modifier
                                .background(
                                    SkeletonLightBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .width(64.dp)
                                .height(18.dp)
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Spacer(
                            modifier
                                .background(
                                    SkeletonLightBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .width(72.dp)
                                .height(18.dp)
                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(
                            modifier
                                .background(
                                    SkeletonLightBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .width(140.dp)
                                .height(18.dp)
                        )
                    }
                }
            }
            is UserDetailUiState.Success -> {
                GlideImage(
                    model = userDetailUiState.userDetail.profilePicture,
                    contentDescription = "profile",
                    modifier = modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .padding(vertical = 10.dp)
                        .fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxSize()
                    ) {
                        Text(
                            text = userDetailUiState.userDetail.name,
                            style = LiftTheme.typography.no1,
                            color = LiftTheme.colorScheme.no5,
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Text(
                            text = "${userDetailUiState.userDetail.height}cm/${userDetailUiState.userDetail.weight}kg",
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no5
                        )
                    }
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "운동 초심자",
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no5
                            )
                            Spacer(modifier = modifier.padding(1.dp))
                            Icon(
                                painter = painterResource(LiftIcon.ChevronRightSharp),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no5
                            )
                        }


                        LiftIconButton(
                            onClick = onClickAlarm,
                            modifier = modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Bell),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
                    }

                }

            }
        }

    }
}







