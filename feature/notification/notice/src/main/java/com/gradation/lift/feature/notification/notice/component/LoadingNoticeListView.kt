package com.gradation.lift.feature.notification.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush


@Composable
fun LoadingNoticeListView(modifier: Modifier =Modifier){
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(8) {
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(SkeletonBrush(), shape = RoundedCornerShape(12.dp))
            )
        }
    }
}