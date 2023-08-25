package com.gradation.lift.feature.my_info.my_info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.my_info.component.profile_detail_view.LoadingProfileDetailView
import com.gradation.lift.feature.my_info.my_info.component.profile_detail_view.ProfileDetailView
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    workCount:Int,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfile: () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(0.dp, 0.dp, 18.dp, 18.dp)
            )
            .padding(16.dp)
    ) {
        when (userDetailUiState) {
            is UserDetailUiState.Fail -> {}
            UserDetailUiState.Loading -> {
                LoadingProfileDetailView(modifier, navigateMyInfoGraphToLoginGraph)
            }

            is UserDetailUiState.Success -> {
                ProfileDetailView(
                    modifier = modifier,
                    userDetail = userDetailUiState.userDetail,
                    navigateMyInfoGraphToLoginGraph = navigateMyInfoGraphToLoginGraph,
                    navigateMyInfoToUpdateProfile = navigateMyInfoToUpdateProfile
                )
            }
        }
        StatisticView(modifier, workCount)
    }
}