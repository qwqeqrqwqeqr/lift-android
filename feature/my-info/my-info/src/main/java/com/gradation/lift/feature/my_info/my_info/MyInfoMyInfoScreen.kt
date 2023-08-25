package com.gradation.lift.feature.my_info.my_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.my_info.component.LoadingProfileDetailView
import com.gradation.lift.feature.my_info.my_info.component.ProfileDetailView
import com.gradation.lift.feature.my_info.my_info.data.MyInfoMyInfoViewModel
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState
import com.gradation.lift.model.utils.ModelDataGenerator.User.userDetailModel

@Composable
fun MyInfoMyInfoRoute(
    modifier: Modifier = Modifier,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfile: () -> Unit,
    navigateUpdateToMyInfo: () -> Unit,
    viewModel: MyInfoMyInfoViewModel = hiltViewModel()
) {

    val workCount: Int by viewModel.workCount.collectAsStateWithLifecycle()
    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()


    MyInfoMyInfoScreen(
        modifier,
        workCount,
        userDetailUiState,
        navigateMyInfoGraphToLoginGraph,
        navigateMyInfoToUpdateProfile,
        navigateUpdateToMyInfo
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyInfoMyInfoScreen(
    modifier: Modifier = Modifier,
    workCount: Int,
    userDetailUiState: UserDetailUiState,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfile: () -> Unit,
    navigateUpdateToMyInfo: () -> Unit
) {
    Surface(
        color = LiftTheme.colorScheme.no17,
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
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
                            navigateMyInfoGraphToLoginGraph = navigateMyInfoGraphToLoginGraph
                        )
                    }
                }
                Row {

                }
            }
        }
    }
}


@Preview
@Composable
fun MyInfoMyInfoScreenPreview() {
    LiftMaterialTheme {
        MyInfoMyInfoScreen(
            workCount = 13,
            userDetailUiState = UserDetailUiState.Success(userDetailModel),
            navigateMyInfoGraphToLoginGraph = { },
            navigateMyInfoToUpdateProfile = {},
            navigateUpdateToMyInfo = {})
    }
}