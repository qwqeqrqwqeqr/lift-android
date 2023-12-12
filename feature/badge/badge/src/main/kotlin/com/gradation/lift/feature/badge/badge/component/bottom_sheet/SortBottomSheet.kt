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
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftBottomSheet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    modifier: Modifier = Modifier,
    sortType: SortType,
    updateSortType: (SortType) -> Unit,
    updateVisibleSortBottomSheet: (Boolean) -> Unit,
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { updateVisibleSortBottomSheet(false) },
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
                        painter = painterResource(id = R.drawable.search_3d),
                        contentDescription = "filterSearch"
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("어떻게 ")
                            withStyle(
                                style = SpanStyle(color = LiftTheme.colorScheme.no4),
                            ) {
                                append("정렬")
                            }
                            append("할까요?")
                        },
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9
                    )
                }
                IconButton(
                    modifier = modifier.size(16.dp),
                    onClick = { updateVisibleSortBottomSheet(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "",
                        tint = LiftTheme.colorScheme.no9,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateSortType(SortType.Number()) },
                    containerColor = if (sortType is SortType.Number) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = SortType.Number().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,

                        )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateSortType(SortType.Name()) },
                    containerColor = if (sortType is SortType.Name) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = SortType.Name().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateSortType(SortType.Newest()) },
                    containerColor = if (sortType is SortType.Newest) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = SortType.Newest().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateSortType(SortType.Oldest()) },
                    containerColor = if (sortType is SortType.Oldest) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no13
                ) {
                    Text(
                        text = SortType.Oldest().getTitleName(),
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }
    }
}

