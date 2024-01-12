package com.gradation.lift.feature.myInfo.updateName.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
internal fun UpdateNameScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "",
                onClick = { }
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

