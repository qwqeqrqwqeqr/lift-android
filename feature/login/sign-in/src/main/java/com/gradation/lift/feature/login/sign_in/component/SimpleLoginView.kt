package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.LoginSignInScreen

@Composable
internal fun SimpleLoginView(modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
        Text(
            text = "간편하게 로그인",
            style = LiftTheme.typography.no5,
            color = LiftTheme.colorScheme.no11,
            modifier = modifier.weight(1f),
            textAlign = TextAlign.Center

        )
        Divider(
            thickness = 1.dp,
            modifier = modifier.weight(1f)
        )
    }
    Spacer(modifier = modifier.padding(8.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterHorizontally),

        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()

    ) {
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginKakao),
            label = "카카오톡",
            modifier = modifier,
        )
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginGoogle),
            label = "구글",
            modifier = modifier
        )
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginNaver),
            label = "네이버",
            modifier = modifier
        )
    }
}

//TODO 간편로그인 로직 연동
@Composable
internal fun SimpleLoginButton(
    painterResource: Painter,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(onClick = {}, modifier.size(52.dp)) {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = Color.Unspecified,

            )
        }
        Text(
            text = label,
            color = LiftTheme.colorScheme.no7,
            style = LiftTheme.typography.no7,
        )
    }
}




