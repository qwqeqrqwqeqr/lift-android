package com.gradation.lift.feature.my_info.my_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.my_info.component.MyInfoListView
import com.gradation.lift.feature.my_info.my_info.component.ProfileView
import com.gradation.lift.feature.my_info.my_info.data.MyInfoMyInfoViewModel
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState
import com.gradation.lift.model.utils.ModelDataGenerator.User.userDetailModel

@Composable
fun MyInfoMyInfoRoute(
    modifier: Modifier = Modifier,
    versionName: String,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfile: () -> Unit,
    navigateMyInfoToUpdate: () -> Unit,
    viewModel: MyInfoMyInfoViewModel = hiltViewModel(),
) {

    val workCount: Int by viewModel.workCount.collectAsStateWithLifecycle()
    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()


    MyInfoMyInfoScreen(
        modifier,
        versionName,
        workCount,
        userDetailUiState,
        navigateMyInfoGraphToLoginGraph,
        navigateMyInfoToUpdateProfile,
        navigateMyInfoToUpdate
    )
}

@Composable
fun MyInfoMyInfoScreen(
    modifier: Modifier = Modifier,
    versionName: String,
    workCount: Int,
    userDetailUiState: UserDetailUiState,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfile: () -> Unit,
    navigateMyInfoToUpdate: () -> Unit
) {
    Surface(
        color = LiftTheme.colorScheme.no17,
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            ProfileView(
                modifier,
                userDetailUiState,
                workCount,
                navigateMyInfoGraphToLoginGraph,
                navigateMyInfoToUpdateProfile
            )
            Spacer(modifier = modifier.padding(9.dp))
            MyInfoListView(modifier, versionName, navigateMyInfoToUpdate)

        }
    }
}


@Preview
@Composable
fun MyInfoMyInfoScreenPreview() {
    LiftMaterialTheme {
        MyInfoMyInfoScreen(
            workCount = 13,
            versionName = "1.0.0",
            userDetailUiState = UserDetailUiState.Success(userDetailModel),
            navigateMyInfoGraphToLoginGraph = { },
            navigateMyInfoToUpdateProfile = {},
            navigateMyInfoToUpdate = {})
    }
}