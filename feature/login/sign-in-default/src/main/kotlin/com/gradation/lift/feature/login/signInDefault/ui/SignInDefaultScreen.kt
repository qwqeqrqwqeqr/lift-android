package com.gradation.lift.feature.login.signInDefault.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.component.textField.LiftPasswordInputTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.signInDefault.data.SignInScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun SignInDefaultScreen(
    modifier: Modifier = Modifier,
    signIn: (String, String) -> Unit,
    navigateSignInDefaultToSignInInLoginGraph: () -> Unit,
    navigateSignInDefaultToVerifyEmailInLoginGraph: () -> Unit,
    signInScreenState: SignInScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "로그인",
                onClick = navigateSignInDefaultToSignInInLoginGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = signInScreenState.snackbarHostState
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
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = "이메일",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        LiftDefaultInputTextField(
                            modifier = modifier,
                            value = signInScreenState.email,
                            onValueChange = signInScreenState.updateEmail,
                            placeHolderValue = "이메일을 입력해주세요",
                            onValueClear = signInScreenState.clearEmail,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    signInScreenState.focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )
                    }
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = "비밀번호",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        LiftPasswordInputTextField(
                            modifier = modifier,
                            value = signInScreenState.password,
                            onValueChange = signInScreenState.updatePassword,
                            placeHolderValue = "비밀번호를 입력해주세요",
                            onValueClear = signInScreenState.clearPassword,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    signInScreenState.focusManager.clearFocus()
                                    signIn(signInScreenState.email, signInScreenState.password)
                                }
                            )
                        )
                    }
                }
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LiftSolidButton(text = "로그인") {
                        signIn(signInScreenState.email, signInScreenState.password)
                    }

                    LiftText(
                        modifier = modifier
                            .noRippleClickable {
                                navigateSignInDefaultToVerifyEmailInLoginGraph()
                            },
                        textStyle = LiftTextStyle.No7,
                        text = "비밀번호를 잊으셨나요?",
                        color = LiftTheme.colorScheme.no4,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}
