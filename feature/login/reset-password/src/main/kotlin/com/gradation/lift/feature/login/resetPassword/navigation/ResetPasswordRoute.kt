package com.gradation.lift.feature.login.resetPassword.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.login.resetPassword.data.ResetPasswordScreenState
import com.gradation.lift.feature.login.resetPassword.data.ResetPasswordState
import com.gradation.lift.feature.login.resetPassword.data.ResetPasswordViewModel
import com.gradation.lift.feature.login.resetPassword.data.UpdatePasswordState
import com.gradation.lift.feature.login.resetPassword.data.rememberResetPasswordScreenState
import com.gradation.lift.feature.login.resetPassword.ui.ResetPasswordScreen
import com.gradation.lift.feature.login.resetPassword.ui.dialog.CancelDialog
import com.gradation.lift.feature.login.resetPassword.ui.dialog.CompleteDialog
import com.gradation.lift.ui.extensions.showImmediatelySnackbar

@Composable
fun ResetPasswordRoute(
    modifier: Modifier = Modifier,
    email: String,
    navigateResetPasswordToSignInDefaultInLoginGraph: () -> Unit,
    viewModel: ResetPasswordViewModel = hiltViewModel(),
    resetPasswordState: ResetPasswordState = viewModel.resetPasswordState,
    resetPasswordScreenState: ResetPasswordScreenState = rememberResetPasswordScreenState(),
) {

    val password: String by resetPasswordState.password.collectAsStateWithLifecycle()
    val passwordVerification: String by resetPasswordState.passwordVerification.collectAsStateWithLifecycle()

    val passwordValidator: Validator by resetPasswordState.passwordValidator.collectAsStateWithLifecycle()
    val passwordVerificationValidator: Validator by resetPasswordState.passwordVerificationValidator.collectAsStateWithLifecycle()
    val isValidPassword: Boolean by resetPasswordState.isValidPassword.collectAsStateWithLifecycle()
    val isValidPasswordVerification: Boolean by resetPasswordState.isValidPasswordVerification.collectAsStateWithLifecycle()

    val updatePasswordState: UpdatePasswordState by viewModel.updatePasswordState.collectAsStateWithLifecycle()
    val updateUpdatePasswordState: (UpdatePasswordState) -> Unit =
        viewModel.updateUpdatePasswordState

    val updatePassword: (String, String) -> Unit = viewModel.updatePassword

    when (val updatePasswordStateResult = updatePasswordState) {
        is UpdatePasswordState.Fail -> {
            LaunchedEffect(updatePasswordStateResult.message) {
                resetPasswordScreenState.snackbarHostState.showImmediatelySnackbar(
                    updatePasswordStateResult.message
                )
                updateUpdatePasswordState(UpdatePasswordState.None)
            }
        }

        UpdatePasswordState.None -> {}
        UpdatePasswordState.Success -> {
            resetPasswordScreenState.updateCompleteDialogView(true)
        }
    }

    BackHandler(onBack = { resetPasswordScreenState.updateCancelDialogView(true) })

    AnimatedVisibility(visible = resetPasswordScreenState.completeDialogView) {
        CompleteDialog(
            modifier = modifier,
            onComplete = navigateResetPasswordToSignInDefaultInLoginGraph
        )
    }

    AnimatedVisibility(visible = resetPasswordScreenState.cancelDialogView) {
        CancelDialog(
            modifier = modifier,
            onClickDialogCancelButton = navigateResetPasswordToSignInDefaultInLoginGraph,
            onClickDialogDismissButton = { resetPasswordScreenState.updateCancelDialogView(false) }
        )
    }

    ResetPasswordScreen(
        modifier,
        email,
        password,
        passwordVerification,
        passwordValidator,
        passwordVerificationValidator,
        isValidPassword,
        isValidPasswordVerification,
        resetPasswordState,
        resetPasswordScreenState,
        updatePassword
    )
}
