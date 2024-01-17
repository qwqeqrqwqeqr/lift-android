package com.gradation.lift.feature.login.termsOfUse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.common.data.TermsOfUseState
import com.gradation.lift.feature.login.termsOfUse.data.state.TermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.data.state.rememberTermsOfUseScreenState
import com.gradation.lift.feature.login.termsOfUse.ui.component.AllConsentView
import com.gradation.lift.feature.login.termsOfUse.ui.component.ConsentView

@Composable
fun TermsOfUseScreen(
    modifier: Modifier = Modifier,
    termsOfUseScreenState: TermsOfUseScreenState,
    signUp: () -> Unit,
    updateTermsOfUseState: (TermsOfUseState) -> Unit,
    navigateTermsOfUseToSignInInLoginGraph: () -> Unit,
    navigateTermsOfUseToTermsOfUseDetailInLoginGraph: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "이용약관 동의",
                onClick = navigateTermsOfUseToSignInInLoginGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = termsOfUseScreenState.snackbarHostState
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
        ) {

            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                ) {
                    AllConsentView(
                        modifier = modifier,
                        contentText = "아래약관에 모두 동의합니다.",
                        checked = termsOfUseScreenState.allConsent.value,
                        onCheckedChange = termsOfUseScreenState.updateAllConsent
                    )

                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                    ) {
                        ConsentView(
                            modifier = modifier,
                            contentText = "이용약관 필수 동의",
                            checked = termsOfUseScreenState.termsOfUseConsent.value,
                            onCheckedChange = termsOfUseScreenState.updateTermsOfUseConsent,
                            onClick = {
                                updateTermsOfUseState(TermsOfUseState.TermsOfUse())
                                navigateTermsOfUseToTermsOfUseDetailInLoginGraph()
                            }
                        )
                        ConsentView(
                            modifier = modifier,
                            contentText = "개인정보 처리방침 필수 동의",
                            checked = termsOfUseScreenState.privacyPolicyConsent.value,
                            onCheckedChange = termsOfUseScreenState.updatePrivacyPolicyConsent,
                            onClick = {
                                updateTermsOfUseState(TermsOfUseState.PrivacyPolicy())
                                navigateTermsOfUseToTermsOfUseDetailInLoginGraph()
                            }
                        )
                        LiftText(
                            modifier = modifier,
                            textStyle = LiftTextStyle.No7,
                            text = "‘선택' 항목에 동의하지 않아도 서비스 이용이 가능합니다. 개인정보 수집 및 이용에 대한 동의를 거부할 권리가 있으며, 동의 거부 시 회원제 서비스 이용이 제한됩니다.",
                            color = LiftTheme.colorScheme.no7,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                LiftSolidButton(
                    text = "회원가입",
                    enabled = termsOfUseScreenState.termsOfUseConsent.value && termsOfUseScreenState.privacyPolicyConsent.value
                ) { signUp() }
            }
        }

    }
}


@Composable
@Preview
fun TermsOfUseScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        TermsOfUseScreen(modifier,
            termsOfUseScreenState = rememberTermsOfUseScreenState(),
            signUp = { },
            updateTermsOfUseState = {},
            navigateTermsOfUseToSignInInLoginGraph = {},
            navigateTermsOfUseToTermsOfUseDetailInLoginGraph = {}
        )
    }
}