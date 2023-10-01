package com.gradation.lift.feature.badge.badge.component.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.FilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    filterType: FilterType,
    updateFilterType: (FilterType) -> Unit,
    updateVisibleFilterBottomSheet: (Boolean) -> Unit,
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { updateVisibleFilterBottomSheet(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = com.gradation.lift.designsystem.R.drawable.search_3d),
                        contentDescription = "filterSearch"
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(color = LiftTheme.colorScheme.no4),
                            ) {
                                append("어떤 뱃지")
                            }
                            append("를 찾아볼까요?")
                        },
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9
                    )
                }
                IconButton(
                    modifier = modifier.size(16.dp),
                    onClick = { updateVisibleFilterBottomSheet(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "",
                        tint = LiftTheme.colorScheme.no9,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateFilterType(FilterType.All()) },
                    containerColor = if (filterType is FilterType.All) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = FilterType.All().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,

                        )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateFilterType(FilterType.Acquired()) },
                    containerColor = if (filterType is FilterType.Acquired) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = FilterType.Acquired().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateFilterType(FilterType.UnAcquired()) },
                    containerColor = if (filterType is FilterType.UnAcquired) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = FilterType.UnAcquired().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }
    }
}