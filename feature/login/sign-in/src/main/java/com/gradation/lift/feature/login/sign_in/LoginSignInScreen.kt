package com.gradation.lift.feature.login.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.Icon
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel(),
) {
    LoginSignInScreen()
}


@Composable
fun LoginSignInScreen(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.padding(48.dp))
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

            SignInView()
            SimpleLoginView()

        }
    }

}


@Composable
fun SignInView(modifier: Modifier = Modifier) {
    Text(text = "이메일")

    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = { },
    ) {
        Text(
            text = "로그인",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }

}

@Composable
fun SimpleLoginView(modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
        Text(
            text = "간편하게 로그인",
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier.weight(1f),
            textAlign = TextAlign.Center

        )
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {

        SimpleLoginButton(painterResource = painterResource(LiftIcon.LoginKakao),label= "카카오톡")
        SimpleLoginButton(painterResource = painterResource(LiftIcon.LoginGoogle),label= "구글")
        SimpleLoginButton(painterResource = painterResource(LiftIcon.LoginNaver),label= "네이버")

    }
}

@Composable
fun SimpleLoginButton(
    painterResource: Painter,
    label: String,
    modifier: Modifier=Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = Color.Unspecified,
                modifier= modifier.size(52.dp)
                )
        }
        Text(

            text = label,
            style = MaterialTheme.typography.labelSmall,
        )
    }

}

@Composable
@Preview
fun LoginSignInPreview() {
    LiftTheme {
        LoginSignInScreen()
    }
}