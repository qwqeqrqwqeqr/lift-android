package com.gradation.lift.myInfo.updateProfilePicture.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SelectedProfilePictureView(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
) {
    Column(modifier=modifier.fillMaxWidth()) {
        if (selectedProfilePicture.isEmpty()) {
            Box(
                modifier
                    .clip(CircleShape)
                    .size(LiftTheme.space.space108)
                    .background(
                        LiftTheme.colorScheme.no1
                    )
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            GlideImage(
                model = selectedProfilePicture,
                contentDescription = "profilePicture",
                modifier = modifier
                    .clip(CircleShape)
                    .size(LiftTheme.space.space108)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}