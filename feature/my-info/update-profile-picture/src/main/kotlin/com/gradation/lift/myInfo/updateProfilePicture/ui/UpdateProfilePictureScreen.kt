package com.gradation.lift.myInfo.updateProfilePicture.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateProfilePictureScreenState


@Composable
fun UpdateProfilePictureScreen(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
    profilePictureList: List<UserProfilePicture>,
    updateUserProfilePicture: () -> Unit,
    updateSelectedProfilePicture: (String) -> Unit,
    navigateUpdateProfileToMyInfoInMyInfoGraph: () -> Unit,
    updateProfilePictureScreenState: UpdateProfilePictureScreenState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 사진 변경",
                onBackClickTopBar = navigateUpdateProfileToMyInfoInMyInfoGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = updateProfilePictureScreenState.snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
                ) {
                    SelectedProfilePictureView(modifier, selectedProfilePicture)
                    ProfilePictureView(
                        modifier,
                        selectedProfilePicture,
                        profilePictureList,
                        updateSelectedProfilePicture
                    )
                }
                LiftSolidButton(
                    text = "저장하기",
                    onClick = updateUserProfilePicture
                )
            }
        }
    }
}

