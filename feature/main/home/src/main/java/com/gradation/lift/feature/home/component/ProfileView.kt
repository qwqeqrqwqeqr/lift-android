package com.gradation.lift.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.UserDetailUiState


@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
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
            modifier
                .size(72.dp),

            )
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            //TODO Profile 이미지 끌고오기
            Box(
                modifier = modifier
                    .background(
                        color = LiftTheme.colorScheme.no4,
                        shape = RoundedCornerShape(96.dp)
                    )
                    .size(72.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고",
                    modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(Color.White)

                )
            }
            Spacer(modifier = modifier.padding(8.dp))
            Column(
                verticalArrangement = Arrangement.Bottom
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
                                .background(SkeletonBrush())
                                .width(54.dp)
                                .height(24.dp),
                        )
                        Spacer(modifier = modifier.padding(4.dp))
                        Box(
                            modifier
                                .background(SkeletonBrush())
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

            }

        }
    }

}