package com.gradation.lift.feature.login.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.component.SignInView
import com.gradation.lift.feature.login.sign_in.component.SimpleLoginView
import com.gradation.lift.navigation.navigation.navigateToFindEmail
import com.gradation.lift.navigation.navigation.navigateToFindPassword
import com.gradation.lift.navigation.navigation.navigateToHome
import com.gradation.lift.navigation.navigation.navigateToLoginSignUp
import com.gradation.lift.ui.DevicePreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KFunction0

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    val emailText = viewModel.email
    val passwordText = viewModel.password
    val updateEmailText = viewModel::updateEmail
    val updatePasswordText = viewModel::updatePassword
    val signInUiState : SignInUiState by viewModel.signInUiState.collectAsStateWithLifecycle()

    LoginSignInScreen(
        modifier = modifier,
        emailText = emailText,
        passwordText = passwordText,
        updateEmailText = updateEmailText,
        updatePasswordText = updatePasswordText,
        onClickFindEmail = { navController.navigateToFindEmail() },
        onClickFindPassword = { navController.navigateToFindPassword() },
        onClickSignUp = { navController.navigateToLoginSignUp() },
        onClickSignIn = viewModel::signIn,
    )

    when(signInUiState){
        SignInUiState.Fail -> {


        }
        SignInUiState.Loading -> {


        }
        SignInUiState.None -> {

        }
        SignInUiState.Success -> {
            navController.navigateToHome()
        }
    }
}


@Composable
fun LoginSignInScreen(
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
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {


            Spacer(modifier = modifier.padding(32.dp))
            Text(
                text = buildAnnotatedString {
                    append("매일매일 운동하고, 기록하고! \n")
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    ) {
                        append("나만의 운동파트너, 리프트")
                    }
                },
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = modifier.padding(24.dp))
            SignInView(
                emailText = emailText,
                updateEmailText = updateEmailText,
                passwordText = passwordText,
                updatePasswordText = updatePasswordText,
                onClickFindEmail = onClickFindEmail,
                onClickFindPassword = onClickFindPassword,
                onClickSignUp = onClickSignUp,
                onClickSignIn = onClickSignIn
            )
            Spacer(modifier = modifier.padding(32.dp))
            SimpleLoginView()



        }
    }
}


@Composable
@DevicePreview
fun LoginSignInPreview() {
    LiftTheme {
        LoginSignInScreen(
            emailText = "",
            updateEmailText = {},
            passwordText = "",
            updatePasswordText = {},
            onClickFindEmail = {},
            onClickFindPassword = {},
            onClickSignUp = {},
            onClickSignIn = {},
        )
    }
}

