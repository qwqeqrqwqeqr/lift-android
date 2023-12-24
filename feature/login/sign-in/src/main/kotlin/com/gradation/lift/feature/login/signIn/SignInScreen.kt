package com.gradation.lift.feature.login.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftGoogleLoginButton
import com.gradation.lift.designsystem.component.button.LiftKakaoLoginButton
import com.gradation.lift.designsystem.component.button.LiftNaverLoginButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.feature.login.signIn.data.state.SignInScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
internal fun SignInScreen(
    modifier: Modifier = Modifier,
    oAuthSignInState: OAuthSignInState,
    signInScreenState: SignInScreenState,
) {
    Scaffold(modifier = modifier, snackbarHost = {
        LiftSnackBar(
            modifier = modifier,
            snackbarHostState = signInScreenState.snackbarHostState
        )
    }) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(
                LiftTheme.space.space132,
                Alignment.CenterVertically
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = LiftTheme.space.space40),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
            ) {
                Image(
                    modifier = modifier.height(LiftTheme.space.space84),
                    painter = painterResource(id = com.gradation.lift.designsystem.R.drawable.logo_v2),
                    contentDescription = "LogoV2"
                )
                LiftMultiStyleText(
                    modifier = modifier,
                    defaultColor = LiftTheme.colorScheme.no3,
                    defaultTextStyle = LiftTextStyle.No2,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "매일매일 운동하고, 기록하고!\n"),
                        TextWithStyle(text = "나만의 운동파트너, 리프트", color = LiftTheme.colorScheme.no4)
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = modifier.padding(horizontal = LiftTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                ) {
                    LiftGoogleLoginButton {}
                    LiftNaverLoginButton { oAuthSignInState.connectOAuthFromNaver }
                    LiftKakaoLoginButton { oAuthSignInState.connectOAuthFromKakao }
                    LiftDefaultButton(text = "이메일로 시작하기") {}
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No7,
                        text = "계정이 없으신가요? ",
                        color = LiftTheme.colorScheme.no7,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        modifier = modifier.noRippleClickable {

                        },
                        textStyle = LiftTextStyle.No7,
                        text = "회원가입",
                        color = LiftTheme.colorScheme.no4,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

