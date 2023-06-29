package com.gradation.lift.feature.login.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftTopBar

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
        onChangePasswordVerificationVisible= viewModel.onChangePasswordVerificationVisible()
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


            }
        }
    }
}