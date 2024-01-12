package com.gradation.lift.feature.myInfo.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileScreenState
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileUiState
import com.gradation.lift.ui.modifier.noRippleClickable


@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateProfileToMyInfoInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateNameInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateInfoInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit,
    profileUiState: ProfileUiState,
    profileScreenState: ProfileScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "프로필 정보",
                onClick = navigateProfileToMyInfoInMyInfoGraph,
                actions = {
                    LiftText(
                        modifier = modifier.noRippleClickable {
                            profileScreenState.updateSignOutDialogView(true)
                        },
                        textStyle = LiftTextStyle.No6,
                        text = "로그아웃",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            )
        },

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
        }
    }
}

