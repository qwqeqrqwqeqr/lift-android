package com.gradation.lift.designsystem.component.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftFilterChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent,
            borderWidth = 0.dp
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = LiftTheme.colorScheme.no1,
            labelColor = LiftTheme.colorScheme.no9,
            iconColor = LiftTheme.colorScheme.no9,

            selectedContainerColor = LiftTheme.colorScheme.no4,
            selectedLabelColor = LiftTheme.colorScheme.no5,
            selectedLeadingIconColor = LiftTheme.colorScheme.no5,
            selectedTrailingIconColor = LiftTheme.colorScheme.no5,
        ),
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = (if (selected) LiftTheme.typography.no3 else LiftTheme.typography.no4)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftOutlineFilterChip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    selectedTextStyle : androidx.compose.ui.text.TextStyle = LiftTheme.typography.no5,
    unselectedTextStyle : androidx.compose.ui.text.TextStyle = LiftTheme.typography.no6,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        border = FilterChipDefaults.filterChipBorder(
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            selectedBorderColor = LiftTheme.colorScheme.no4,
            selectedBorderWidth = 2.dp
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = LiftTheme.colorScheme.no1,
            labelColor = LiftTheme.colorScheme.no9,
            iconColor = LiftTheme.colorScheme.no9,

            selectedContainerColor = LiftTheme.colorScheme.no5,
            selectedLabelColor = LiftTheme.colorScheme.no4,
            selectedLeadingIconColor = LiftTheme.colorScheme.no5,
            selectedTrailingIconColor = LiftTheme.colorScheme.no5,
        ),
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = (if (selected) selectedTextStyle else unselectedTextStyle)
            )
        }
    )
}


@Composable
@Preview
fun LiftFilterChipPreview() {

    LiftMaterialTheme {
        Column {
            LiftFilterChip(
                text = "리프트",
                selected = true,
                onClick = {}
            )
            LiftFilterChip(
                text = "리프트",
                selected = false,
                onClick = {}
            )
            LiftOutlineFilterChip(
                text = "리프트",
                selected = true,
                onClick = {}
            )
            LiftOutlineFilterChip(
                text = "리프트",
                selected = false,
                onClick = {}
            )
        }

    }
}
