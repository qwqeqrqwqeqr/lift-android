package com.gradation.lift.termsPolicy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.utils.TermsPolicyContent.PRIVACY_POLICY_CONTENT
import com.gradation.lift.ui.utils.TermsPolicyContent.TERMS_OF_USE_CONTENT

@Composable
fun TermsPolicyScreen(
    modifier: Modifier = Modifier,
    navigateTermsPolicyToTermsPolicyDetailMyInfoGraph: (String, String) -> Unit,
    navigateTermsPolicyToMyInfoInMyInfoGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "약관 및 정책",
                onClick = navigateTermsPolicyToMyInfoInMyInfoGraph
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
            listOf(
                Pair("서비스 이용약관", TERMS_OF_USE_CONTENT),
                Pair("개인정보 처리방침", PRIVACY_POLICY_CONTENT),
            ).forEach {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateTermsPolicyToTermsPolicyDetailMyInfoGraph(it.first, it.second)
                        }
                        .padding(
                            vertical = LiftTheme.space.space16,
                            horizontal = LiftTheme.space.space20
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = it.first,
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Start
                    )
                    Icon(
                        modifier = modifier
                            .size(LiftTheme.space.space14),
                        painter = painterResource(LiftIcon.ChevronRight),
                        contentDescription = "ChevronRight",
                        tint = LiftTheme.colorScheme.no2,
                    )
                }
            }
        }
    }
}

