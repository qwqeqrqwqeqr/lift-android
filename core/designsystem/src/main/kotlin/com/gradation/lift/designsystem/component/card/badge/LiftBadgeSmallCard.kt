package com.gradation.lift.designsystem.component.card.badge

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LiftBadgeSmallCard(
    modifier: Modifier = Modifier,
    isDefault: Boolean,
    badgeName: String,
    badgeUrl: String,
    selected: Boolean = false,
) {
    LiftDefaultContainer(
        modifier = modifier.border(
            width = LiftTheme.space.space2,
            color = if (selected) LiftTheme.colorScheme.no4 else Color.Transparent,
            shape = RoundedCornerShape(LiftTheme.space.space6)
        ),
        backGroundColor = if (selected) LiftTheme.colorScheme.no17 else LiftTheme.colorScheme.no5,
        verticalPadding = LiftTheme.space.space12,
        horizontalPadding = LiftTheme.space.space12,
        shape = RoundedCornerShape(LiftTheme.space.space6)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isDefault) {
                LiftIconBox(
                    icon = LiftIcon.BadgeCircle,
                    iconType = IconType.Vector,
                    iconBoxSize = IconBoxSize.Size48
                )
            } else {
                GlideImage(
                    modifier = modifier.size(LiftTheme.space.space48),
                    model = badgeUrl,
                    contentDescription = badgeUrl
                )
            }

            LiftText(
                textStyle = LiftTextStyle.No8,
                text = badgeName,
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
@Preview
fun LiftBadgeSmallCardPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(modifier = modifier.fillMaxSize()) {
            LiftBadgeSmallCard(
                modifier,
                isDefault = true,
                badgeName = "초심자",
                badgeUrl = "",
                selected = true
            )
        }
    }
}