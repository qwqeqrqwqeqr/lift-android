package com.gradation.lift.feature.myInfo.myInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoUiState
import com.gradation.lift.feature.myInfo.myInfo.ui.component.HeaderView
import com.gradation.lift.feature.myInfo.myInfo.ui.component.MyInfoListView


@Composable
fun MyInfoScreen(
    modifier: Modifier = Modifier,
    navigateMyInfoGraphToNoticeGraph: () -> Unit,
    navigateMyInfoToProfileInMyInfoGraph: () -> Unit,
    navigateToOssScreen: () -> Unit,
    myInfoUiState: MyInfoUiState,
    myInfoScreenState: MyInfoScreenState,
) {
    Scaffold(
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = myInfoScreenState.snackbarHostState
            )
            Spacer(modifier = modifier.padding(LiftTheme.space.space72))
        }
    ) { padding ->
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no17)
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            HeaderView(modifier, myInfoUiState, navigateMyInfoToProfileInMyInfoGraph)
            MyInfoListView(
                modifier,
                navigateMyInfoGraphToNoticeGraph,
                navigateToOssScreen,
                myInfoScreenState
            )
        }
    }
}


