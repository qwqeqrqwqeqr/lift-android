package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftBannerImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BannerView(modifier: Modifier = Modifier) {
    GlideImage(
        modifier=modifier,
        model = LiftBannerImage.Banner1,
        contentDescription = "banner1",
        contentScale = ContentScale.FillWidth
    )
}