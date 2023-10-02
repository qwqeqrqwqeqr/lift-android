package com.gradation.lift.feature.badge.badge.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import kotlinx.datetime.LocalDateTime


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeDialog(
    modifier: Modifier = Modifier,
    badge: AllBadge.AcquireBadge,
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
                .padding(top = 16.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.halo),
                    contentDescription = "halo",
                    modifier = modifier.fillMaxWidth()
                )
                GlideImage(model = badge.url,
                    modifier= modifier.width(maxWidth/2),
                    contentDescription = "badge")
            }

            Column(
                modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    badge.name,
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no3
                )
                Text(
                    text = badge.badgeTimeStamp.date.toString()
                        .replace("-", "."),
                    color = LiftTheme.colorScheme.no6,
                    style = LiftTheme.typography.no6,
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
fun BadgeDialogPreview() {
    LiftMaterialTheme {
        BadgeDialog(
            modifier = Modifier,
            badge = AllBadge.AcquireBadge(
                0,
                "초심자",
                "설명",
                "힌트",
                "",
                LocalDateTime(2023, 9, 12, 8, 15, 30)
            ),
            updateVisibleBadgeDialog = {}
        )
    }
}