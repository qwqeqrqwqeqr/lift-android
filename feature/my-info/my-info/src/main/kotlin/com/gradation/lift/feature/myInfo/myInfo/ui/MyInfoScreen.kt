package com.gradation.lift.feature.myInfo.myInfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.myInfo.ui.component.MyInfoListView
import com.gradation.lift.feature.myInfo.myInfo.ui.component.ProfileView
import com.gradation.lift.feature.myInfo.myInfo.data.state.UserDetailUiState


@Composable
fun MyInfoScreen(
    modifier: Modifier = Modifier,
    versionName: String,
    badgeCount: Int,
    workCount: Int,
    userDetailUiState: UserDetailUiState,
    signOut: () -> Unit,
    navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit,
    navigateMyInfoToUpdateInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToNotificationGraph: () -> Unit,
    navigateMyInfoGraphToBadgeGraph: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier.padding(padding),
            color = LiftTheme.colorScheme.no17,
        ) {
            Column(modifier = modifier.fillMaxSize()) {
                ProfileView(
                    modifier,
                    userDetailUiState,
                    badgeCount,
                    workCount,
                    signOut,
                    navigateProfileToUpdateProfilePictureInMyInfoGraph,
                    navigateMyInfoGraphToNotificationGraph,
                    navigateMyInfoGraphToBadgeGraph
                )
                Spacer(modifier = modifier.padding(9.dp))
                MyInfoListView(
                    modifier,
                    versionName,
                    navigateMyInfoToUpdateInMyInfoGraph,
                    navigateMyInfoGraphToNotificationGraph,
                )

            }
        }
    }
}


