package com.gradation.lift.designsystem.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftKakaoLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no41
        else LiftTheme.colorScheme.no40,
        label = "contentColor"
    )

    val textColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no11
        else LiftTheme.colorScheme.no11,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space20,Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier=modifier.size(LiftTheme.space.space20),
            painter = painterResource(id = LiftIcon.Kakao),
            contentDescription = "LoginKakao",
            tint = Color.Unspecified
        )
        LiftButtonText(
            modifier = modifier,
            text = "카카오톡으로 시작하기",
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun LiftGoogleLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no43
        else LiftTheme.colorScheme.no42,
        label = "contentColor"
    )

    val textColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no11
        else LiftTheme.colorScheme.no11,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space20,Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier=modifier.size(LiftTheme.space.space20),
            painter = painterResource(id = LiftIcon.Google),
            contentDescription = "LoginGoogle",
            tint = Color.Unspecified
        )
        LiftButtonText(
            modifier = modifier,
            text = "Google로 시작하기",
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun LiftNaverLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no45
        else LiftTheme.colorScheme.no44,
        label = "contentColor"
    )

    val textColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no11
        else LiftTheme.colorScheme.no11,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space20,Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier=modifier.size(LiftTheme.space.space20),
            painter = painterResource(id = LiftIcon.Naver),
            contentDescription = "LoginNaver",
            tint = Color.Unspecified
        )
        LiftButtonText(
            modifier = modifier,
            text = "Naver로 시작하기",
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}