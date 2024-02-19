package com.gradation.lift.feature.myInfo.cancelMembership.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun CancelMembershipScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "약관 및 정책",
                onClick = {}
            )
        },
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(top = LiftTheme.space.space32),

            ) {

        }
    }
}


