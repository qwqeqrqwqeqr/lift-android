package com.gradation.lift.feature.home.badge.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.ui.modifier.noRippleClickable


@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
    badge:Badge,
    createUserBadge: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
        horizontalAlignment = Alignment.End
    ) {
        Icon(
            modifier = modifier
                .size(LiftTheme.space.space20)
                .noRippleClickable { createUserBadge(badge.id) },
            painter = painterResource(LiftIcon.Close),
            contentDescription = "Close",
            tint = LiftTheme.colorScheme.no10
        )

        LiftMultiStyleText(
            modifier = modifier.fillMaxWidth(),
            defaultColor = LiftTheme.colorScheme.no9,
            defaultTextStyle = LiftTextStyle.No1,
            textAlign = TextAlign.Center,
            textWithStyleList = listOf(
                TextWithStyle("뱃지를 "),
                TextWithStyle("획득", color = LiftTheme.colorScheme.no4),
                TextWithStyle("했어요!"),
            )
        )
    }
}