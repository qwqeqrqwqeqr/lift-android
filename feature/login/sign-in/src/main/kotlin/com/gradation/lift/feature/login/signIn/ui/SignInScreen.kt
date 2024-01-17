package com.gradation.lift.feature.login.signIn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.signIn.data.state.OAuthSignInState
import com.gradation.lift.feature.login.signIn.data.state.SignInScreenState
import com.gradation.lift.feature.login.signIn.ui.component.SignInView
import com.gradation.lift.feature.login.signIn.ui.component.TitleView

@Composable
internal fun SignInScreen(
    modifier: Modifier = Modifier,
    oAuthSignInState: OAuthSignInState,
    signInScreenState: SignInScreenState,
    navigateSignInToSignInDefaultInLoginGraph: () -> Unit,
    navigateSignInToSignUpCreateEmailDefaultInLoginGraph: () -> Unit,
) {


    Scaffold(modifier = modifier, snackbarHost = {
        LiftSnackBar(
            modifier = modifier,
            snackbarHostState = signInScreenState.snackbarHostState
        )
    }) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(
                LiftTheme.space.space132,
                Alignment.CenterVertically
            )
        ) {
            TitleView(modifier)
            SignInView(
                modifier,
                oAuthSignInState,
                navigateSignInToSignInDefaultInLoginGraph,
                navigateSignInToSignUpCreateEmailDefaultInLoginGraph,
                signInScreenState
            )
        }
    }
}

