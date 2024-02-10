package com.gradation.lift.feature.badge.badge.component.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.temp.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType

@Composable
fun BadgeFilterView(
    modifier: Modifier = Modifier,
    sortType: SortType,
    filterType: FilterType,
    updateVisibleFilterBottomSheet: (Boolean) -> Unit,
    updateVisibleSortBottomSheet: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LiftOutlineButton(
            modifier = modifier.height(28.dp),
            contentPadding = PaddingValues(
                start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = { updateVisibleSortBottomSheet(true) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "정렬", color = LiftTheme.colorScheme.no14,
                    style = LiftTheme.typography.no7
                )
                Divider(
                    modifier = modifier
                        .height(14.dp)
                        .width(1.dp),
                    color = LiftTheme.colorScheme.no4

                )
                Text(text = sortType.getTitleName(), style = LiftTheme.typography.no7)
                Icon(
                    painter = painterResource(id = LiftIcon.DropDown),
                    contentDescription = "sortIcon"
                )
            }
        }
        LiftOutlineButton(modifier = modifier.height(28.dp),
            contentPadding = PaddingValues(
                start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = { updateVisibleFilterBottomSheet(true) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "뱃지",
                    color = LiftTheme.colorScheme.no14,
                    style = LiftTheme.typography.no7
                )
                Divider(
                    modifier = modifier
                        .height(14.dp)
                        .width(1.dp),
                    color = LiftTheme.colorScheme.no4
                )
                Text(
                    text = filterType.getTitleName(),
                    style = LiftTheme.typography.no7
                )
                Icon(
                    painter = painterResource(id = LiftIcon.DropDown),
                    contentDescription = "filterIcon"
                )
            }
        }
    }
}