package com.gradation.lift.feature.badge.badge.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.smallButton.LiftBadgeFilterSmallButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftSortFilterSmallButton
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeSmallCard
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BadgeStoreContentView(
    modifier: Modifier = Modifier,
    sortType: SortType,
    filterType: FilterType,
    userBadgeList: List<UserBadge>,
    currentTotalBadgeCount: Int,
    acquiredBadgeCount: Int,
    unacquiredBadgeCount: Int,
    badgeScreenState: BadgeScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20,
                bottom = LiftTheme.space.space12
            ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space48)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftEmptyContainer(
                modifier = modifier.weight(1f),
                backGroundColor = LiftTheme.colorScheme.no59,
                verticalPadding = LiftTheme.space.space12,
                horizontalPadding = LiftTheme.space.space16,
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                ) {
                    LiftIconBox(
                        icon = LiftIcon.Badge,
                        iconType = IconType.Vector,
                        iconBoxSize = IconBoxSize.Size24
                    )
                    Column {
                        LiftText(
                            textStyle = LiftTextStyle.No5,
                            text = "획득 뱃지",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )
                        LiftText(
                            textStyle = LiftTextStyle.No1,
                            text = "${acquiredBadgeCount}개",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
            LiftEmptyContainer(
                modifier = modifier.weight(1f),
                backGroundColor = LiftTheme.colorScheme.no1,
                verticalPadding = LiftTheme.space.space12,
                horizontalPadding = LiftTheme.space.space16
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                ) {
                    LiftIconBox(
                        icon = LiftIcon.BadgeDisabled,
                        iconType = IconType.Vector,
                        iconBoxSize = IconBoxSize.Size24
                    )
                    Column {
                        LiftText(
                            textStyle = LiftTextStyle.No5,
                            text = "미획득 뱃지",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )
                        LiftText(
                            textStyle = LiftTextStyle.No1,
                            text = "${unacquiredBadgeCount}개",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
        ) {
            LiftMultiStyleText(
                modifier,
                LiftTheme.colorScheme.no9,
                LiftTextStyle.No6,
                listOf(
                    TextWithStyle(text = "총 "),
                    TextWithStyle(
                        text = "$currentTotalBadgeCount",
                        style = LiftTextStyle.No5
                    ),
                    TextWithStyle(text = "개의 뱃지")
                ),
                TextAlign.Start
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                LiftSortFilterSmallButton(modifier.noRippleClickable {
                    badgeScreenState.updateSortBottomSheetView(
                        true
                    )
                }, sortType.getTitleName())
                LiftBadgeFilterSmallButton(modifier.noRippleClickable {
                    badgeScreenState.updateFilterBottomSheetView(
                        true
                    )
                }, filterType.getTitleName())
            }
        }
    }
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = LiftTheme.space.space20,
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20
            ),
        verticalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space8,
        ),
        horizontalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space12,
        )
    ) {
        userBadgeList.forEach {
            when (it) {
                is UserBadge.AcquireBadge -> LiftBadgeSmallCard(
                    modifier.noRippleClickable {
                        badgeScreenState.updateBadgeDetailDialogView(true to it)
                    }, false, it.name, it.url
                )

                is UserBadge.UnacquiredBadge -> LiftBadgeSmallCard(
                    modifier.noRippleClickable {
                        badgeScreenState.updateBadgeDetailDialogView(true to it)
                    }, true, it.name, it.url
                )
            }
        }
    }
}