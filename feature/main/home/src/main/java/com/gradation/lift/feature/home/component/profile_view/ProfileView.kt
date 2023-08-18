package com.gradation.lift.feature.home.component.profile_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.profile_view.profile_detail_view.LoadingProfileDetailView
import com.gradation.lift.feature.home.component.profile_view.profile_detail_view.ProfileDetailView
import com.gradation.lift.feature.home.data.state.UserDetailUiState

@Composable
internal fun ProfileView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.logo_extension_kor),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = modifier.size(72.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = modifier
                .fillMaxWidth()
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
        ) {
            when (userDetailUiState) {
                is UserDetailUiState.Fail -> {
                    Spacer(modifier = modifier.height(72.dp))
                }
                UserDetailUiState.Loading -> LoadingProfileDetailView(modifier = modifier)
                is UserDetailUiState.Success -> ProfileDetailView(
                    modifier = modifier,
                    userDetail = userDetailUiState.userDetail,
                )
            }


        }
    }
}


