package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.LoginSignInScreen
import com.gradation.lift.feature.login.sign_in.SignInUiState
import com.gradation.lift.feature.login.sign_in.component.detail.SignInHelperView
import com.gradation.lift.feature.login.sign_in.component.detail.SignUpView
import com.gradation.lift.ui.DevicePreview
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    passwordText: String,
    updatePasswordText: (String) -> Unit,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickSignIn: () -> Unit,
) {
    Text(
        text = "이메일",
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = emailText,
        onValueChange = updateEmailText,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("이메일을 입력해주세요.") },
        singleLine = true,
    )
    Spacer(modifier = modifier.padding(8.dp))
    Text(
        text = "비밀번호",
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = passwordText,
        onValueChange = updatePasswordText,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("비밀번호를 입력해주세요.") },
        singleLine = true,
    )
    Spacer(modifier = modifier.padding(4.dp))

    SignInHelperView(
        modifier = modifier,
        onClickFindEmail = onClickFindEmail,
        onClickFindPassword = onClickFindPassword
    )
    Spacer(modifier = modifier.padding(36.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = onClickSignIn,
        ) {
            Text(
                text = "로그인",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        SignUpView(modifier = modifier, onClickSignUp = onClickSignUp)
    }
}

