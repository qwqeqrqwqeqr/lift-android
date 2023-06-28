package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon

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
    Spacer(modifier = modifier.padding(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp)
    ) {
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginKakao),
            label = "카카오톡",
            modifier = modifier.weight(2f)
        )
        Spacer(modifier = modifier.weight(1f))
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginGoogle),
            label = "구글",
            modifier = modifier.weight(2f)
        )
        Spacer(modifier = modifier.weight(1f))
        SimpleLoginButton(
            painterResource = painterResource(LiftIcon.LoginNaver),
            label = "네이버",
            modifier = modifier.weight(2f)
        )
    }
}

@Composable
fun SimpleLoginButton(
    painterResource: Painter,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource,
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = modifier.size(52.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}


