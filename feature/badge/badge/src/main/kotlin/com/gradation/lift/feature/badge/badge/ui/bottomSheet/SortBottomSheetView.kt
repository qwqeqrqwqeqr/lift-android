package com.gradation.lift.feature.badge.badge.ui.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.selector.LiftDefaultSelector
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.data.state.BadgeStoreState
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 뱃지 정렬 바텀 시트
 * @since 2024-02-26 16:11:24
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortBottomSheetView(
    modifier: Modifier = Modifier,
    sortType: SortType,
    badgeStoreState: BadgeStoreState,
    badgeStoreScreenState: BadgeScreenState,
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { badgeStoreScreenState.updateSortBottomSheetView(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    bottom = LiftTheme.space.space20
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    LiftMultiStyleText(
                        defaultColor = LiftTheme.colorScheme.no9,
                        defaultTextStyle = LiftTextStyle.No2,
                        textAlign = TextAlign.Start,
                        textWithStyleList = listOf(
                            TextWithStyle("어떻게 정렬", color = LiftTheme.colorScheme.no4),
                            TextWithStyle("할까요?"),
                        )
                    )
                }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space10)
                        .noRippleClickable {
                            badgeStoreScreenState.updateSortBottomSheetView(false)
                        },
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "Close",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                with(SortType.Number()) {
                    LiftDefaultSelector(
                        modifier.fillMaxWidth(),
                        this.getTitleName(),
                        sortType == this,
                    ) {
                        badgeStoreState.updateSortType(this)
                        badgeStoreScreenState.updateSortBottomSheetView(false)
                    }
                }
                with(SortType.Name()) {
                    LiftDefaultSelector(
                        modifier.fillMaxWidth(),
                        this.getTitleName(),
                        sortType == this
                    ) {
                        badgeStoreState.updateSortType(this)
                        badgeStoreScreenState.updateSortBottomSheetView(false)
                    }
                }

                with(SortType.Newest()) {
                    LiftDefaultSelector(
                        modifier.fillMaxWidth(),
                        this.getTitleName(),
                        sortType == this,
                    ) {
                        badgeStoreState.updateSortType(this)
                        badgeStoreScreenState.updateSortBottomSheetView(false)
                    }
                }
                with(SortType.Oldest()) {
                    LiftDefaultSelector(
                        modifier.fillMaxWidth(),
                        this.getTitleName(),
                        sortType == this,
                    ) {
                        badgeStoreState.updateSortType(this)
                        badgeStoreScreenState.updateSortBottomSheetView(false)
                    }
                }

            }
        }
    }
}


