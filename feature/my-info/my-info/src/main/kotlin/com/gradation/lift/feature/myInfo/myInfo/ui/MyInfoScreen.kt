package com.gradation.lift.feature.myInfo.myInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    navigateMyInfoToTermsPolicyInMyInfoGraph: () -> Unit,
    navigateToOssScreen: () -> Unit,
    myInfoUiState: MyInfoUiState,
    myInfoScreenState: MyInfoScreenState,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        HeaderView(modifier, myInfoUiState, navigateMyInfoToProfileInMyInfoGraph)
        MyInfoListView(
            modifier,
            navigateMyInfoGraphToNoticeGraph,
            navigateMyInfoToTermsPolicyInMyInfoGraph,
            navigateToOssScreen,
            myInfoScreenState
        )
    }
}


