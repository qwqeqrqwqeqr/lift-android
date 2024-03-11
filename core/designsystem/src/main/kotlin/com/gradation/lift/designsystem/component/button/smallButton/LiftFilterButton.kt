package com.gradation.lift.designsystem.component.button.smallButton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


/**
 * [LiftLabelFilterSmallButton]
 * 라벨 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftLabelFilterSmallButton(
    modifier: Modifier = Modifier,
    labelType: Set<Int>,
    selectedAll: Boolean,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Label,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "라벨",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            if (selectedAll) {
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = "전체",
                    color = LiftTheme.colorScheme.no4,
                    textAlign = TextAlign.Start
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    labelType.forEach {
                        RoutineLabel(modifier = modifier.size(LiftTheme.space.space12), id = it)
                    }
                }
            }
        }
    }
}


/**
 * [LiftWeekdayFilterSmallButton]
 * 요일 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftWeekdayFilterSmallButton(
    modifier: Modifier = Modifier,
    weekdayType: String,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Calendar,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "요일",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = weekdayType,
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}


/**
 * [LiftBadgeFilterSmallButton]
 * 뱃지 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftBadgeFilterSmallButton(
    modifier: Modifier = Modifier,
    filterType: String,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.BadgeSmall,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "뱃지",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = filterType,
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}


/**
 * [LiftFavoriteFilterSmallButton]
 * 관심 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftFavoriteFilterSmallButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = if (checked) LiftIcon.HeartFilled else LiftIcon.HeartEmpty,
                iconType = IconType.Painter,
                iconBoxSize = IconBoxSize.Size14,
            )
            LiftText(
                textStyle = if (checked) LiftTextStyle.No5 else LiftTextStyle.No6,
                text = "관심",
                color = if (checked) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13,
                textAlign = TextAlign.Start
            )
        }
    }
}

/**
 * [LiftPopularFilterSmallButton]
 * 인기 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftPopularFilterSmallButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Fire,
                iconType = IconType.Painter,
                iconBoxSize = IconBoxSize.Size14,
                tint = if (checked) Color.Unspecified else LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = if (checked) LiftTextStyle.No5 else LiftTextStyle.No6,
                text = "인기",
                color = if (checked) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13,
                textAlign = TextAlign.Start
            )
        }
    }
}


/**
 * [LiftRecommendFilterSmallButton]
 * 추천 필터 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftRecommendFilterSmallButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Thumb,
                iconType = IconType.Painter,
                iconBoxSize = IconBoxSize.Size14,
                tint = if (checked) Color.Unspecified else LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = if (checked) LiftTextStyle.No5 else LiftTextStyle.No6,
                text = "추천",
                color = if (checked) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13,
                textAlign = TextAlign.Start
            )
        }
    }
}


@Composable
@Preview
fun LiftFilterButtonPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column {
            LiftLabelFilterSmallButton(modifier, labelType = setOf(1, 2, 3), selectedAll = false)
            LiftWeekdayFilterSmallButton(modifier, "월")
            LiftBadgeFilterSmallButton(modifier, "획득")
            LiftFavoriteFilterSmallButton(modifier, true)
            LiftFavoriteFilterSmallButton(modifier, false)
            LiftPopularFilterSmallButton(modifier, true)
            LiftPopularFilterSmallButton(modifier, false)
            LiftRecommendFilterSmallButton(modifier, true)
            LiftRecommendFilterSmallButton(modifier, false)
        }
    }
}
