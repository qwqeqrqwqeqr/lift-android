package com.gradation.lift.feature.login.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.utils.DevicePreview

@Composable
fun LoginCompleteRoute(
    navigateSignUpToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginCompleteViewModel = hiltViewModel(),
) {


    LoginCompleteScreen(
        modifier, navigateSignUpToSignIn
    )
}


@Composable
internal fun LoginCompleteScreen(
    modifier: Modifier = Modifier,
    onClickComplete: () -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.gradation.lift.designsystem.R.drawable.lift_congrats),
                contentDescription = "",
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append("리프트 회원가입을 \n")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no4),

                        ) {
                        append("축하드려요!")
                    }
                },
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no11,
            )
            Spacer(modifier = modifier.padding(16.dp))
            LiftButton(
                modifier = modifier.fillMaxWidth(),
                onClick = onClickComplete,
            ) {
                Text(
                    text = "시작하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
            }
        }
    }
}


@DevicePreview
@Composable
internal fun LoginCompleteScreenPreview() {
    LiftMaterialTheme(isDarkTheme = false) {
        LoginCompleteScreen(
            onClickComplete = {}
        )
    }
}