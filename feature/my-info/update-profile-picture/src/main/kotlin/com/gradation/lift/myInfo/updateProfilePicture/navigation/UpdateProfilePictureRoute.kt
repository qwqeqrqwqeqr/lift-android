package com.gradation.lift.myInfo.updateProfilePicture.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.myInfo.updateProfilePicture.data.UpdateProfilePictureViewModel
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateProfilePictureScreenState
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateProfilePictureState
import com.gradation.lift.myInfo.updateProfilePicture.data.state.rememberUpdateProfilePictureScreenState
import com.gradation.lift.myInfo.updateProfilePicture.ui.UpdateProfilePictureScreen
import com.gradation.lift.ui.extensions.showImmediatelySnackbar


@Composable
fun UpdateProfilePictureRoute(
    modifier: Modifier = Modifier,
    navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: UpdateProfilePictureViewModel = hiltViewModel(),
    updateProfilePictureScreenState: UpdateProfilePictureScreenState = rememberUpdateProfilePictureScreenState(),
) {

    val updateProfilePictureState: UpdateProfilePictureState by viewModel.updateProfilePictureState.collectAsStateWithLifecycle()
    val selectedProfilePicture: String by viewModel.selectedProfilePicture.collectAsStateWithLifecycle()
    val profilePictureList: List<UserProfilePicture> by viewModel.profilePictureList.collectAsStateWithLifecycle()


    val updateUserProfilePicture: () -> Unit = viewModel.updateUserProfilePicture
    val updateSelectedProfilePicture: (String) -> Unit = viewModel.updateSelectedProfilePicture
    val updateUpdateUserDetailState: (UpdateProfilePictureState) -> Unit =
        viewModel.updateUpdateProfilePictureState



    BackHandler(onBack = { navigateUpdateProfileToMyInfoInMyInfoGraph() })

    when (val state: UpdateProfilePictureState = updateProfilePictureState) {
        is UpdateProfilePictureState.Fail -> {
            LaunchedEffect(true) {
                updateProfilePictureScreenState.snackbarHostState.showImmediatelySnackbar(
                    message = state.message,

                    )
                updateUpdateUserDetailState(UpdateProfilePictureState.None)
            }
        }

        UpdateProfilePictureState.None -> {}
        UpdateProfilePictureState.Success -> {
            LaunchedEffect(true) {
                navigateUpdateProfileToMyInfoInMyInfoGraph()
            }
        }
    }

    UpdateProfilePictureScreen(
        modifier,
        selectedProfilePicture,
        profilePictureList,
        updateUserProfilePicture,
        updateSelectedProfilePicture,
        navigateUpdateProfileToMyInfoInMyInfoGraph,
        updateProfilePictureScreenState
    )
}