package com.gradation.lift.designsystem.component.card.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.label.BadgeNameLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LiftBadgeLargeCard(
    modifier: Modifier = Modifier,
    badgeName: String,
    description: String,
    badgeUrl: String,
    color: Color,
    backgroundColor: Color,
    badgeTimeStamp: LocalDateTime,
    onClickDismiss: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    LiftEmptyContainer(
        modifier = modifier
            .width(LiftTheme.space.space280)
            .height(LiftTheme.space.space360),
        backGroundColor = backgroundColor,
        shape = RoundedCornerShape(LiftTheme.space.space12)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = LiftTheme.space.space12,
                            topEnd = LiftTheme.space.space12
                        )
                    )
            ) {
                GlideImage(
                    model = LiftImage.BadgeSmallHalo,
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    contentDescription = "halo",
                    contentScale = ContentScale.FillWidth
                )

                Column(
                    modifier = modifier.padding(bottom = LiftTheme.space.space24),
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
                            tint = LiftTheme.colorScheme.no13
                        )
                    }
                    GlideImage(
                        modifier = modifier.size(LiftTheme.space.space116),
                        model = badgeUrl,
                        contentDescription = badgeUrl
                    )
                }
            }
            Column(
                modifier = modifier
                    .background(
                        LiftTheme.colorScheme.no5.copy(0.4f), RoundedCornerShape(
                            bottomEnd = LiftTheme.space.space12,
                            bottomStart = LiftTheme.space.space12
                        )
                    )
                    .fillMaxSize()
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
                    textColor = color,
                    backgroundColor = color.copy(0.2f)
                )
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = description,
                    color = LiftTheme.colorScheme.no7,
                    textAlign = TextAlign.Start
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(LiftTheme.space.space32),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No7,
                        text = badgeTimeStamp.toJavaLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun LiftBadgeLargeCardPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.fridayLabelColor)
        ) {
            LiftBadgeLargeCard(
                modifier,
                badgeName = "뱃지",
                description = "누구나 얻을 수 있는 초심자용 뱃지,\n지금부터 열심히 운동해요!",
                badgeUrl = "",
                color = LiftTheme.colorScheme.no26,
                backgroundColor = LiftTheme.colorScheme.no29,
                badgeTimeStamp = LocalDateTime(2024, 1, 2, 18, 12, 0),
                onClickDismiss = {}
            )
        }
    }
}

