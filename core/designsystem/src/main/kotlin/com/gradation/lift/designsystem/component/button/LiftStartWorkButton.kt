package com.gradation.lift.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LiftStartWorkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
    ) {
        GlideImage(
            modifier = modifier.width(LiftTheme.space.space72),
            model = LiftImage.WorkLogo,
            contentDescription = ""
        )
        Box(
            modifier = modifier
                .shadow(
                    elevation = LiftTheme.space.space4,
                    spotColor = LiftTheme.colorScheme.no36,
                    ambientColor = LiftTheme.colorScheme.no36,
                    shape = CircleShape
                )
                .border(
                    width = LiftTheme.space.space2,
                    color = LiftTheme.colorScheme.no5,
                    shape = CircleShape
                )
                .background(
                    LiftTheme.colorScheme.no4,
                    CircleShape
                )
                .size(LiftTheme.space.space72)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = modifier
                    .width(LiftTheme.space.space42)
                    .height(LiftTheme.space.space28),
                painter = painterResource(id = LiftIcon.Work),
                contentDescription = "work",
                tint = LiftTheme.colorScheme.no5
            )
        }
    }
}