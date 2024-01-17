package com.gradation.lift.feature.login.resetPassword.ui

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
import com.gradation.lift.feature.login.resetPassword.data.ResetPasswordScreenState
import com.gradation.lift.feature.login.resetPassword.data.ResetPasswordState

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    passwordVerification: String,
    passwordValidator: Validator,
    passwordVerificationValidator: Validator,
    isValidPassword: Boolean,
    isValidPasswordVerification: Boolean,
    resetPasswordState: ResetPasswordState,
    resetPasswordScreenState: ResetPasswordScreenState,
    updatePassword: (String, String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "비밀번호 재설정",
                onClick = {
                    resetPasswordScreenState.updateCancelDialogView(true)
                }
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = resetPasswordScreenState.snackbarHostState
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
                        TextWithStyle(text = "재설정 하실\n"),
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
                            onValueChange = resetPasswordState.updatePassword,
                            onValueClear = resetPasswordState.clearPassword,
                            placeHolderValue = "비밀번호를 입력해주세요.",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            isError = !passwordValidator.status,
                            keyboardActions = KeyboardActions(onNext = {
                                resetPasswordScreenState.focusManager.moveFocus(
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
                                onValueChange = resetPasswordState.updatePasswordVerification,
                                onValueClear = resetPasswordState.clearPasswordVerification,
                                placeHolderValue = "비밀번호를 다시 입력해주세요.",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                isError = !passwordVerificationValidator.status,
                                keyboardActions = KeyboardActions(onDone = {
                                    resetPasswordScreenState.focusManager.clearFocus()
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
                        text = "비밀번호 변경",
                        enabled = passwordValidator.status &&
                                passwordVerificationValidator.status
                    ) {
                        updatePassword(email, password)
                    }
                }
            }
        }
    }
}
