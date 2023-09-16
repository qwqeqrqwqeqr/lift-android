package com.gradation.lift.my_info.update_profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.my_info.update_profile.component.HeaderView
import com.gradation.lift.my_info.update_profile.component.NavigationView
import com.gradation.lift.my_info.update_profile.component.ProfilePictureView
import com.gradation.lift.my_info.update_profile.component.SelectedProfilePictureView
import com.gradation.lift.my_info.update_profile.data.MyInfoUpdateProfileViewModel
import com.gradation.lift.my_info.update_profile.data.state.UpdateUserDetailState

@Composable
fun MyInfoUpdateProfileRoute(
    modifier: Modifier = Modifier,
    navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: MyInfoUpdateProfileViewModel = hiltViewModel(),
) {

    val updateUserDetailState: UpdateUserDetailState by viewModel.updateUserDetailState.collectAsStateWithLifecycle()
    val selectedProfilePicture: String by viewModel.selectedProfilePicture.collectAsStateWithLifecycle()
    val profilePictureList: List<UserProfilePicture> by viewModel.profilePictureList.collectAsStateWithLifecycle()
    val updateCondition: Boolean by viewModel.updateCondition.collectAsStateWithLifecycle()


    val updateUserProfilePicture: () -> Unit = viewModel.updateUserProfilePicture()
    val updateSelectedProfile: (String) -> Unit = viewModel.updateSelectedProfile()
    val updateUpdateUserDetailState: (UpdateUserDetailState) -> Unit =
        viewModel.updateUpdateUserDetailState()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }


    BackHandler(onBack = { navigateUpdateProfileToMyInfoInMyInfoGraph() })

    when (val updateUserDetailStateResult: UpdateUserDetailState = updateUserDetailState) {
        is UpdateUserDetailState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = updateUserDetailStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateUserDetailState(UpdateUserDetailState.None)
            }
        }

        UpdateUserDetailState.None -> {}
        UpdateUserDetailState.Success -> {
            LaunchedEffect(true) {
                navigateUpdateProfileToMyInfoInMyInfoGraph()
            }
        }
    }

    MyInfoUpdateProfileScreen(
        modifier,
        selectedProfilePicture,
        profilePictureList,
        updateCondition,
        updateUserProfilePicture,
        updateSelectedProfile,
        navigateUpdateProfileToMyInfoInMyInfoGraph,
        snackbarHostState
    )


}

@Composable
fun MyInfoUpdateProfileScreen(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
    profilePictureList: List<UserProfilePicture>,
    updateCondition: Boolean,
    updateUserProfilePicture: () -> Unit,
    updateSelectedProfile: (String) -> Unit,
    navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit,
    snackbarHostState: SnackbarHostState

) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필사진 변경",
                onBackClickTopBar = navigateUpdateProfileToMyInfoInMyInfoGraph
            )
        },
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no5,
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Spacer(modifier = modifier.padding(32.dp))
                    HeaderView(modifier)
                    Spacer(modifier = modifier.padding(16.dp))
                    SelectedProfilePictureView(modifier, selectedProfilePicture)
                    Spacer(modifier = modifier.padding(13.dp))
                    ProfilePictureView(
                        modifier,
                        selectedProfilePicture,
                        profilePictureList,
                        updateSelectedProfile
                    )
                }


                NavigationView(
                    modifier,
                    updateCondition,
                    updateUserProfilePicture
                )
            }
        }
    }
}

@Composable
@Preview
fun MyInfoUpdateProfileScreenPreview() {
    LiftMaterialTheme {
        MyInfoUpdateProfileScreen(
            selectedProfilePicture = "",
            profilePictureList = listOf(),
            updateCondition = true,
            updateUserProfilePicture = {},
            updateSelectedProfile = {},
            navigateUpdateProfileToMyInfoInMyInfoGraph = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}