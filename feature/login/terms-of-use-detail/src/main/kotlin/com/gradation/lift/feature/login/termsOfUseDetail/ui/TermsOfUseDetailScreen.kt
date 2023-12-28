package com.gradation.lift.feature.login.termsOfUseDetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.termsOfUseDetail.data.TermsOfUseDetailScreenState

@Composable
fun TermsOfUseDetailScreen(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    navigateTermsOfUseDetailToTermsOfUseInLoginGraph: () -> Unit,
    termsOfUseDetailScreenState: TermsOfUseDetailScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = title,
                onClick = navigateTermsOfUseDetailToTermsOfUseInLoginGraph
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
                    bottom = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                )
                .verticalScroll(termsOfUseDetailScreenState.scrollState),
        ) {

            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No6,
                    text = content,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Start
                )
                LiftSolidButton(text = "확인") { navigateTermsOfUseDetailToTermsOfUseInLoginGraph() }
            }
        }

    }
}
