package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun SignUpView(
    modifier: Modifier = Modifier,
    signUp: () -> Unit,
    focusManager: FocusManager,
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
                        textDecoration = TextDecoration.Underline
                    ),
            onClick = {
                focusManager.clearFocus()
                signUp()
            },
        )

    }
}

