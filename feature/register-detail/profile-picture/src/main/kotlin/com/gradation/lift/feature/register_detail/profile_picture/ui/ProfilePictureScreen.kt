package com.gradation.lift.feature.register_detail.profile_picture.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.registerDetail.common.ui.component.ProgressNumberView
import com.gradation.lift.feature.register_detail.profile_picture.data.ProfilePictureScreenState
import com.gradation.lift.feature.register_detail.profile_picture.ui.component.HeaderView
import com.gradation.lift.feature.register_detail.profile_picture.ui.component.ProfilePictureView
import com.gradation.lift.feature.register_detail.profile_picture.ui.component.SelectedProfilePictureView
import com.gradation.lift.model.model.picture.UserProfilePicture


@Composable
internal fun ProfilePictureScreen(
    modifier: Modifier = Modifier,
    profilePictureList: List<UserProfilePicture>,
    profilePicture: String,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateProfilePicture: (String) -> Unit,
    updateCancelDialogView: (Boolean) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    createUserDetail: () -> Unit,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    navigateToHeightWeightInRegisterDetailGraph: () -> Unit,
    profilePictureScreenState: ProfilePictureScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "추가정보 입력",
                onClick = { updateCancelDialogView(true) }
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = profilePictureScreenState.snackbarHostState
            )
        }
    ) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressNumberView(
                modifier,
                currentRegisterProgressNumber,
                totalRegisterProgressNumber,
                navigateToNameInRegisterDetailGraph = {
                    updateCurrentRegisterProgressNumber(1)
                    navigateToNameInRegisterDetailGraph()
                },
                navigateToGenderInRegisterDetailGraph = {
                    updateCurrentRegisterProgressNumber(2)
                    navigateToGenderInRegisterDetailGraph()
                },
                navigateToHeightWeightInRegisterDetailGraph = {
                    updateCurrentRegisterProgressNumber(3)
                    navigateToHeightWeightInRegisterDetailGraph()
                },
            )
            Spacer(modifier = modifier.height(LiftTheme.space.space28))
            HeaderView(modifier)
            Spacer(modifier = modifier.height(LiftTheme.space.space32))

            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
            ) {
                SelectedProfilePictureView(modifier, profilePicture)
                ProfilePictureView(
                    modifier,
                    profilePicture,
                    profilePictureList,
                    updateProfilePicture
                )
            }
            Spacer(modifier = modifier.height(LiftTheme.space.space36))
            AnimatedVisibility(
                visible = profilePicture.isNotEmpty()
            ) {
                LiftSolidButton(text = "완료"
                ) {
                    createUserDetail()
                }
            }
        }
    }
}