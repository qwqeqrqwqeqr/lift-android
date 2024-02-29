package com.gradation.lift.myInfo.termsPolicyDetail.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun TermsPolicyDetailScreen(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    navigateTermsPolicyDetailToTermsPolicyMyInfoGraph: () -> Unit,
    scrollState: ScrollState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = title,
                onClick = navigateTermsPolicyDetailToTermsPolicyMyInfoGraph
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = content,
                color = LiftTheme.colorScheme.no11,
                textAlign = TextAlign.Start
            )
        }
    }
}

