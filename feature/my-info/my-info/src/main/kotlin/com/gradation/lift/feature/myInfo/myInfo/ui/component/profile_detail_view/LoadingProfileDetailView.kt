package com.gradation.lift.feature.myInfo.myInfo.ui.component.profile_detail_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun LoadingProfileDetailView(
    modifier: Modifier = Modifier,
    signOut: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        Box(
            modifier = modifier
                .background(SkeletonBrush(), shape = CircleShape)
                .size(72.dp)
        )
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = modifier.padding(vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = modifier
                            .width(64.dp)
                            .height(24.dp)
                            .background(SkeletonBrush(), shape = RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Box(
                        modifier = modifier
                            .width(64.dp)
                            .height(24.dp)
                            .background(SkeletonBrush(), shape = RoundedCornerShape(8.dp))
                    )
                }
                Box(
                    modifier = modifier
                        .background(
                            LiftTheme.colorScheme.no1, RoundedCornerShape(6.dp)
                        )
                        .padding(5.dp)
                        .noRippleClickable { signOut() }
                ) {
                    Text(
                        text = "로그아웃",
                        style = LiftTheme.typography.no6,
                        color = LiftTheme.colorScheme.no2
                    )
                }
            }
        }

    }
}

