package com.gradation.lift.feature.login.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.component.ToggleVisible
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginSignUpRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignUpViewModel = hiltViewModel(),
) {


    LoginSignUpScreen(
        modifier = modifier,
        onTopBarBackClick = { navController.popBackStack() },
        emailText = viewModel.email,
        passwordText = viewModel.password,
        passwordVerificationText = viewModel.passwordVerification,
        updateEmailText = viewModel.updateEmail(),
        updatePasswordText = viewModel.updatePassword(),
        updatePasswordVerificationText = viewModel.updatePasswordVerification(),
        passwordVisible = viewModel.passwordVisible,
        passwordVerificationVisible = viewModel.passwordVerificationVisible,
        passwordVisualTransformation = viewModel.passwordVisualTransformation,
        passwordVerificationVisualTransformation = viewModel.passwordVerificationVisualTransformation,
        clearPassword = viewModel.clearPassword(),
        clearPasswordVerification = viewModel.clearPasswordVerification(),
        onChangePasswordVisible = viewModel.onChangePasswordVisible(),
        onChangePasswordVerificationVisible = viewModel.onChangePasswordVerificationVisible()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginSignUpScreen(
    modifier: Modifier = Modifier,
    onTopBarBackClick: () -> Unit,
    emailText: String,
    passwordText: String,
    passwordVerificationText: String,
    updateEmailText: (String) -> Unit,
    updatePasswordText: (String) -> Unit,
    updatePasswordVerificationText: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVerificationVisible: Boolean,
    passwordVisualTransformation: VisualTransformation,
    passwordVerificationVisualTransformation: VisualTransformation,
    clearPassword: () -> Unit,
    clearPasswordVerification: () -> Unit,
    onChangePasswordVisible: (Boolean) -> Unit,
    onChangePasswordVerificationVisible: (Boolean) -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "회원가입",
                    onBackClick = onTopBarBackClick,
                )
            },
        ) { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                val focusManager = LocalFocusManager.current

                Text(
                    text = "이메일",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = emailText,
                    onValueChange = updateEmailText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text("사용할 이메일을 입력해주세요.") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                Spacer(modifier = modifier.padding(18.dp))

                Text(
                    text = "비밀번호",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = passwordText,
                    onValueChange = updatePasswordText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text("영문, 숫자 조합 8~16자 이내") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    visualTransformation = passwordVisualTransformation,
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ToggleVisible(
                                checked = passwordVisible,
                                onCheckedChange = onChangePasswordVisible,
                                modifier = modifier.size(24.dp)
                            )
                            Spacer(modifier = modifier.padding(4.dp))
                            IconButton(
                                onClick = clearPassword,
                                modifier = modifier.size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(LiftIcon.Cancel),
                                    contentDescription = "",
                                    tint = Color.Unspecified,
                                )
                            }
                            Spacer(modifier = modifier.padding(8.dp))
                        }
                    }
                )
                Spacer(modifier = modifier.padding(18.dp))

                Text(
                    text = "비밀번호 확인",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = passwordVerificationText,
                    onValueChange = updatePasswordVerificationText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text("비밀번호를 한번 더 입력해주세요") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    visualTransformation = passwordVerificationVisualTransformation,
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ToggleVisible(
                                checked = passwordVerificationVisible,
                                onCheckedChange = onChangePasswordVerificationVisible,
                                modifier = modifier.size(24.dp)
                            )
                            Spacer(modifier = modifier.padding(4.dp))
                            IconButton(
                                onClick = clearPasswordVerification,
                                modifier = modifier.size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(LiftIcon.Cancel),
                                    contentDescription = "",
                                    tint = Color.Unspecified,
                                )
                            }
                            Spacer(modifier = modifier.padding(8.dp))
                        }
                    }
                )
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { },
                ) {
                    Text(
                        text = "다음",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

            }
        }
    }
}


@DevicePreview
@Composable
fun LoginSignInPreview() {
    LiftTheme {
        LoginSignUpScreen(
            modifier = Modifier,
            onTopBarBackClick = { },
            emailText = "",
            passwordText = "",
            passwordVerificationText = "",
            updateEmailText = {},
            updatePasswordText = {},
            updatePasswordVerificationText = { },
            passwordVisible = true,
            passwordVerificationVisible = true,
            passwordVisualTransformation = VisualTransformation.None,
            passwordVerificationVisualTransformation = VisualTransformation.None,
            clearPassword = { },
            clearPasswordVerification = { },
            onChangePasswordVisible = { },
            onChangePasswordVerificationVisible = {}
        )
    }
}