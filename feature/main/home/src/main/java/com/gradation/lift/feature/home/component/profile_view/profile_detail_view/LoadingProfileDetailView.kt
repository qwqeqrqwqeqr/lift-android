package com.gradation.lift.feature.home.component.profile_view.profile_detail_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonLightBrush

@Composable
internal fun LoadingProfileDetailView(modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(CircleShape)
            .size(72.dp)
            .background(SkeletonLightBrush())

    )
    Spacer(modifier = modifier.padding(10.dp))
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(
                modifier
                    .background(
                        SkeletonLightBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(64.dp)
                    .height(18.dp)
            )
            Spacer(modifier = modifier.padding(4.dp))
            Spacer(
                modifier
                    .background(
                        SkeletonLightBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(72.dp)
                    .height(18.dp)
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier
                    .background(
                        SkeletonLightBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(140.dp)
                    .height(18.dp)
            )
        }
    }
}