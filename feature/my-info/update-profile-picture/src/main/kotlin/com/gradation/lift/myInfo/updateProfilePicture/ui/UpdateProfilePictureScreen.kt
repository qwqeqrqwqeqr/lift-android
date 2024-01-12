package com.gradation.lift.myInfo.updateProfilePicture.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.myInfo.updateProfilePicture.ui.component.HeaderView
import com.gradation.lift.myInfo.updateProfilePicture.ui.component.NavigationView
import com.gradation.lift.myInfo.updateProfilePicture.ui.component.ProfilePictureView
import com.gradation.lift.myInfo.updateProfilePicture.ui.component.SelectedProfilePictureView


@Composable
fun UpdateProfilePictureScreen(
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
        UpdateProfilePictureScreen(
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