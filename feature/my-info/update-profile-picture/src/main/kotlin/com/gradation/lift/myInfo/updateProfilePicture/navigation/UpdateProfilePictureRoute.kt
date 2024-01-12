package com.gradation.lift.myInfo.updateProfilePicture.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.myInfo.updateProfilePicture.data.UpdatePictureProfileViewModel
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateUserDetailState
import com.gradation.lift.myInfo.updateProfilePicture.ui.UpdateProfilePictureScreen


@Composable
fun UpdateProfilePictureRoute(
    modifier: Modifier = Modifier,
    navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: UpdatePictureProfileViewModel = hiltViewModel(),
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

    UpdateProfilePictureScreen(
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