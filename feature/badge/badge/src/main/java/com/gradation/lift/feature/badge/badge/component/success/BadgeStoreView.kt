package com.gradation.lift.feature.badge.badge.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeState


@Composable
fun BadgeStoreView(
    modifier: Modifier,
    badgeState: BadgeState,
    sortType: SortType,
    filterType: FilterType,
    updateSelectedBadge: (AllBadge) -> Unit,
    updateVisibleBadgeDialog: (Boolean) -> Unit,
    updateVisibleFilterBottomSheet: (Boolean) -> Unit,
    updateVisibleSortBottomSheet: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "뱃지보관함",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3
            )
            Text(
                text = buildAnnotatedString {
                    append("총 ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(700)
                        ),
                    ) {
                        append("${badgeState.currentBadgeCount}개")
                    }
                    append("의 뱃지")
                },
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9,
            )
        }
        BadgeFilterView(
            modifier,
            sortType,
            filterType,
            updateVisibleFilterBottomSheet,
            updateVisibleSortBottomSheet
        )
        Divider(
            modifier = modifier.fillMaxWidth(),
            thickness = 8.dp,
            color = LiftTheme.colorScheme.no17
        )
        BadgeListView(modifier, badgeState, updateSelectedBadge, updateVisibleBadgeDialog)
    }
}
