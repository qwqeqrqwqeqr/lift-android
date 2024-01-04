package com.gradation.lift.feature.login.signIn.ui.component

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftGoogleLoginButton
import com.gradation.lift.designsystem.component.button.LiftKakaoLoginButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.signIn.data.state.ConnectState
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun SignInView(
    modifier:Modifier= Modifier,
    oAuthSignInState: OAuthSignInState,
    navigateSignInToSignInDefaultInLoginGraph: () -> Unit,
    navigateSignInToSignUpCreateEmailDefaultInLoginGraph: () -> Unit,
){
    val launcher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when (it.resultCode) {

                Activity.RESULT_OK -> {
                    oAuthSignInState.updateConnectState(
                        ConnectState.Success(
                            LoginMethod.Google()
                        )
                    )
                }
            }
        }
    Column(
        modifier = modifier.padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
        ) {
            LiftGoogleLoginButton {
                oAuthSignInState.getGoogleClient().signOut()
                launcher.launch(
                    oAuthSignInState.getGoogleClient().signInIntent
                )
            }
            LiftKakaoLoginButton { oAuthSignInState.connectOAuthFromKakao() }
            LiftDefaultButton(text = "이메일로 시작하기") { navigateSignInToSignInDefaultInLoginGraph() }
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
                    navigateSignInToSignUpCreateEmailDefaultInLoginGraph()
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