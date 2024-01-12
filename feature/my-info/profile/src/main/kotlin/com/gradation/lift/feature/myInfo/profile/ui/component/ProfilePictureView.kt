package com.gradation.lift.feature.myInfo.profile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileState
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePictureView(
    modifier: Modifier = Modifier,
    navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit,
    profileState: ProfileState,
) {
    Box(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5),
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            modifier = modifier
                .size(LiftTheme.space.space108)
                .align(Alignment.Center),
            model = profileState.profilePicture,
            contentDescription = "profilePicture"
        )
        Box(
            modifier = modifier
                .offset(x = LiftTheme.space.space8, y = LiftTheme.space.space8)
                .size(LiftTheme.space.space32)
                .background(LiftTheme.colorScheme.no1, CircleShape)
                .align(Alignment.BottomEnd)
                .noRippleClickable { navigateProfileToUpdateProfilePictureInMyInfoGraph() }
        ) {
            Icon(
                modifier = modifier
                    .align(Alignment.Center)
                    .size(LiftTheme.space.space16),
                painter = painterResource(id = LiftIcon.Pencil),
                contentDescription = "RoutineProfilePictureEdit",
                tint = LiftTheme.colorScheme.no6
            )
        }
    }
}