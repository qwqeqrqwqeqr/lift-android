package com.gradation.lift.feature.badge.badge.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.AllBadge

@Composable
fun LockBadgeDialog(
    modifier: Modifier = Modifier,
    badge: AllBadge.UnacquiredBadge,
    updateVisibleBadgeDialog: (Boolean) -> Unit,
) {
    LiftDialog(onDismissRequest = { updateVisibleBadgeDialog(false) }) {
        Column(
            modifier
                .clip(RoundedCornerShape(24.dp))
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                IconButton(onClick = { updateVisibleBadgeDialog(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "close",
                        tint = Color.Unspecified,
                    )
                }
            }
            Box(
                modifier = modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(LiftTheme.colorScheme.no1),

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = modifier.size(54.dp),
                    painter = painterResource(id = LiftIcon.LockFilled),
                    contentDescription = "lock",
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    badge.name,
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no3
                )
                Text(
                    badge.description,
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.padding(16.dp))
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { updateVisibleBadgeDialog(false) },
                ) {
                    Text(
                        text = "확인",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LockBadgeDialogPreview() {
    LiftMaterialTheme {
        LockBadgeDialog(
            modifier = Modifier,
            badge = AllBadge.UnacquiredBadge(0, "초심자", "설명", "힌트", ""),
            updateVisibleBadgeDialog = {}
        )
    }
}