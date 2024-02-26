package com.gradation.lift.designsystem.component.card.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.label.BadgeNameLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDefaultBadgeLargeCard(
    modifier: Modifier = Modifier,
    badgeName: String,
    hint: String,
    onClickDismiss: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    LiftEmptyContainer(
        modifier = modifier
            .width(LiftTheme.space.space280),
        shape = RoundedCornerShape(LiftTheme.space.space12),
        backGroundColor = LiftTheme.colorScheme.no15
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = LiftTheme.space.space20,
                        vertical = LiftTheme.space.space16
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                LiftIconBox(
                    modifier = modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onClickDismiss() },
                    icon = LiftIcon.Close,
                    iconType = IconType.Vector,
                    iconBoxSize = IconBoxSize.Size16,
                    tint = LiftTheme.colorScheme.no14
                )
            }
            Column(
                modifier = modifier.padding(vertical = LiftTheme.space.space28),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LiftDefaultBadgeLargeIcon(modifier)
            }
            Column(
                modifier = modifier
                    .background(
                        LiftTheme.colorScheme.no5.copy(0.4f),
                        RoundedCornerShape(
                            bottomEnd = LiftTheme.space.space12,
                            bottomStart = LiftTheme.space.space12
                        )
                    )
                    .fillMaxWidth()
                    .padding(
                        horizontal = LiftTheme.space.space20,
                        vertical = LiftTheme.space.space12
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                BadgeNameLabel(
                    modifier = modifier,
                    badgeName = badgeName,
                    textColor = LiftTheme.colorScheme.no9,
                    backgroundColor = LiftTheme.colorScheme.no58
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No8,
                        text = "획득방법",
                        color = LiftTheme.colorScheme.no6,
                        textAlign = TextAlign.Start
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No5,
                        text = hint,
                        color = LiftTheme.colorScheme.no7,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = modifier)
                }
            }
        }
    }
}


@Composable
@Preview
fun LiftDefaultBadgeLargeCardPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(modifier = modifier.fillMaxSize()) {
            LiftDefaultBadgeLargeCard(
                modifier,
                badgeName = "초심자",
                hint = "운동을 꾸준히 하고 계신다면 달성하기 쉬워요",
                onClickDismiss = {}
            )
        }
    }
}

