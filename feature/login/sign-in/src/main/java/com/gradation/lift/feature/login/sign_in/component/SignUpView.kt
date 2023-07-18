package com.gradation.lift.feature.login.sign_in.component.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.LoginSignInScreen

@Composable
internal fun SignUpView(
    modifier: Modifier = Modifier,
    onClickSignUp: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "계정이 없으신가요?",
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no7,
        )
        Spacer(modifier = Modifier.padding(1.dp))
        ClickableText(
            text = AnnotatedString("회원가입"),
            style = LiftTheme.typography.no7 +
                    TextStyle(
                        color = LiftTheme.colorScheme.no11,
                        textDecoration = TextDecoration.Underline),
            onClick = { _ ->
                onClickSignUp()
            },
        )

    }
}

