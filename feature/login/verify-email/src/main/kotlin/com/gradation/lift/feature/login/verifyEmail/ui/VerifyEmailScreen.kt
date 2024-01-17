package com.gradation.lift.feature.login.verifyEmail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.core.text.isDigitsOnly
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftAuthenticationInputTextField
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.verifyEmail.data.EmailAuthenticationState
import com.gradation.lift.feature.login.verifyEmail.data.VerifyEmailScreenState
import com.gradation.lift.feature.login.verifyEmail.data.VerifyEmailState
import com.gradation.lift.ui.mapper.toText
import kotlinx.datetime.LocalTime

@Composable
fun VerifyEmailScreen(
    modifier: Modifier = Modifier,
    email: String,
    emailAuthenticationCode: String,
    authenticationTimer: LocalTime,
    emailValidator: Validator,
    authenticationCodeValidator: Validator,
    createEmailAuthenticationCode: (String) -> Unit,
    validateEmailAuthentication: (String, Int?) -> Unit,
    navigateVerifyEmailToSignInDefaultInLoginGraph: () -> Unit,
    verifyEmailState: VerifyEmailState,
    emailAuthenticationState: EmailAuthenticationState,
    verifyEmailScreenState: VerifyEmailScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "비밀번호 재설정",
                onClick = navigateVerifyEmailToSignInDefaultInLoginGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = verifyEmailScreenState.snackbarHostState
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
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LiftMultiStyleText(
                    defaultColor = LiftTheme.colorScheme.no11,
                    defaultTextStyle = LiftTextStyle.No1,
                    textAlign = TextAlign.Center,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "비밀번호 재설정을 위해\n"),
                        TextWithStyle(text = "이메일", color = LiftTheme.colorScheme.no4),
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
                            text = "이메일",
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        LiftAuthenticationInputTextField(
                            value = email,
                            onValueChange = verifyEmailState.updateEmail,
                            placeHolderValue = "이메일을 입력해주세요.",
                            sendAuthenticationCode = {
                                createEmailAuthenticationCode(email)
                                verifyEmailScreenState.focusManager.clearFocus()
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            authenticationMessage = when (emailAuthenticationState) {
                                is EmailAuthenticationState.Fail -> "인증번호 전송"
                                EmailAuthenticationState.None -> "인증번호 전송"
                                is EmailAuthenticationState.Success -> "재전송"
                                EmailAuthenticationState.Loading -> "전송중"
                            },
                            isError = !emailValidator.status,
                            keyboardActions = KeyboardActions(onDone = {
                                createEmailAuthenticationCode(
                                    email
                                )
                                verifyEmailScreenState.focusManager.clearFocus()
                            }),
                        )
                        LiftText(
                            modifier = modifier.fillMaxWidth(),
                            textStyle = LiftTextStyle.No7,
                            text = emailValidator.message,
                            color = LiftTheme.colorScheme.no12,
                            textAlign = TextAlign.Start
                        )
                    }


                    AnimatedVisibility(
                        visible =
                        verifyEmailScreenState.authenticationCodeTextFieldView
                    ) {
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            LiftDefaultInputTextField(
                                value = emailAuthenticationCode,
                                onValueChange = verifyEmailState.updateEmailAuthenticationCode,
                                placeHolderValue = "인증번호를 입력해주세요",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                isError = !authenticationCodeValidator.status,
                                keyboardActions = KeyboardActions(onDone = {
                                    validateEmailAuthentication(
                                        email,
                                        emailAuthenticationCode.toIntOrNull()
                                    )
                                    verifyEmailScreenState.focusManager.clearFocus()
                                }),
                            )
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                LiftText(
                                    modifier = modifier,
                                    textStyle = LiftTextStyle.No7,
                                    text = if (authenticationCodeValidator.status) "인증번호가 전송되었습니다." else authenticationCodeValidator.message,
                                    color = if (authenticationCodeValidator.status) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no12,
                                    textAlign = TextAlign.Start
                                )
                                LiftText(
                                    modifier = modifier,
                                    textStyle = LiftTextStyle.No7,
                                    text = authenticationTimer.toText(),
                                    color = if (authenticationCodeValidator.status) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no12,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = verifyEmailScreenState.authenticationButtonView
                    ) {
                        LiftSolidButton(
                            text = "인증 확인",
                            enabled = emailAuthenticationCode.isNotEmpty() && emailAuthenticationCode.isDigitsOnly()
                        ) {
                            validateEmailAuthentication(
                                email,
                                emailAuthenticationCode.toIntOrNull()
                            )
                        }
                    }
                }
            }
        }
    }
}
