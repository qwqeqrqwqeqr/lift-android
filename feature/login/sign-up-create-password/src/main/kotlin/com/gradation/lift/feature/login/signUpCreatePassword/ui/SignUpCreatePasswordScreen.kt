package com.gradation.lift.feature.login.signUpCreatePassword.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftPasswordInputTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.signUpCreatePassword.data.CreatePasswordState
import com.gradation.lift.feature.login.signUpCreatePassword.data.SignUpCreatePasswordScreenState

@Composable
fun SignUpCreatePasswordScreen(
    modifier: Modifier = Modifier,
    password: String,
    passwordVerification: String,
    passwordValidator: Validator,
    passwordVerificationValidator: Validator,
    isValidPassword: Boolean,
    isValidPasswordVerification: Boolean,
    createPasswordState: CreatePasswordState,
    signUpCreatePasswordScreenState: SignUpCreatePasswordScreenState,
    updateCurrentLoginMethodState: () -> Unit,
    navigateSignUpCreatePasswordToTermsOfUseInLoginGraph: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "회원가입",
                onClick = {
                    signUpCreatePasswordScreenState.updateCancelDialogView(true)
                }
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = signUpCreatePasswordScreenState.snackbarHostState
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
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LiftMultiStyleText(
                    defaultColor = LiftTheme.colorScheme.no11,
                    defaultTextStyle = LiftTextStyle.No1,
                    textAlign = TextAlign.Center,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "회원가입에 사용할\n"),
                        TextWithStyle(text = "비밀번호", color = LiftTheme.colorScheme.no4),
                        TextWithStyle(text = "을 입력해주세요."),
                    )
                )


                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        LiftText(
                            modifier = modifier.fillMaxWidth(),
                            textStyle = LiftTextStyle.No3,
                            text = "비밀번호",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        LiftPasswordInputTextField(
                            value = password,
                            onValueChange = createPasswordState.updatePassword,
                            onValueClear = createPasswordState.clearPassword,
                            placeHolderValue = "비밀번호를 입력해주세요.",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            isError = !passwordValidator.status,
                            keyboardActions = KeyboardActions(onNext = {
                                signUpCreatePasswordScreenState.focusManager.moveFocus(
                                    FocusDirection.Down
                                )
                            }),
                            isValid = isValidPassword
                        )
                        LiftText(
                            modifier = modifier.fillMaxWidth(),
                            textStyle = LiftTextStyle.No7,
                            text = passwordValidator.message,
                            color = LiftTheme.colorScheme.no12,
                            textAlign = TextAlign.Start
                        )
                    }
                    AnimatedVisibility(
                        visible = password.isNotEmpty() && passwordValidator.status
                    ) {
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            LiftPasswordInputTextField(
                                value = passwordVerification,
                                onValueChange = createPasswordState.updatePasswordVerification,
                                onValueClear = createPasswordState.clearPasswordVerification,
                                placeHolderValue = "비밀번호를 다시 입력해주세요.",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                isError = !passwordVerificationValidator.status,
                                keyboardActions = KeyboardActions(onDone = {
                                    signUpCreatePasswordScreenState.focusManager.clearFocus()
                                }),
                                isValid = isValidPasswordVerification
                            )
                            LiftText(
                                modifier = modifier.fillMaxWidth(),
                                textStyle = LiftTextStyle.No7,
                                text = passwordVerificationValidator.message,
                                color = LiftTheme.colorScheme.no12,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                AnimatedVisibility(
                    visible = password.isNotEmpty() &&
                            passwordVerification.isNotEmpty() &&
                            passwordValidator.status &&
                            passwordVerificationValidator.status
                ) {
                    LiftSolidButton(
                        text = "다음",
                        enabled = passwordValidator.status &&
                                passwordVerificationValidator.status
                    ) {
                        navigateSignUpCreatePasswordToTermsOfUseInLoginGraph()
                        updateCurrentLoginMethodState()
                    }
                }
            }
        }
    }
}
