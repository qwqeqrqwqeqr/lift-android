package com.gradation.lift.feature.home.component.profile_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.profile_view.profile_detail_view.LoadingProfileDetailView
import com.gradation.lift.feature.home.component.profile_view.profile_detail_view.ProfileDetailView
import com.gradation.lift.feature.home.data.UserDetailUiState

@Composable
internal fun ProfileView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    onClickAlarm: () -> Unit,
    onClickType: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = modifier
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .background(
                    color = LiftTheme.colorScheme.no4,
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(12.dp, 10.dp)
                .fillMaxWidth()
        ) {
            when (userDetailUiState) {
                is UserDetailUiState.Fail -> {
                    Spacer(modifier = modifier.height(72.dp))
                }
                UserDetailUiState.Loading -> LoadingProfileDetailView(modifier = modifier)
                is UserDetailUiState.Success -> ProfileDetailView(
                    modifier = modifier,
                    userDetail = userDetailUiState.userDetail,
                    onClickAlarm = onClickAlarm,
                    onClickType = onClickType,
                )
            }


        }

        Spacer(modifier = modifier.padding(10.dp))
        BadgeView(modifier = modifier)
    }

}


